package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaEstateListAdapter
import com.example.friendapplication.adapter.NigeriaEstateListAdapter
import com.example.friendapplication.model.KenyaEstate
import com.example.friendapplication.model.NigeriaEstate
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class KenyaEstateItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaEstates: MutableList<KenyaEstate>
    private lateinit var kenyaestatelistAdapter: KenyaEstateListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_estate_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaEstateItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaEstates = ArrayList()
        kenyaestatelistAdapter = KenyaEstateListAdapter(
            this@KenyaEstateItemsActivity,mKenyaEstates)
        mRecyclerView.adapter = kenyaestatelistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("estates_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaEstateItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaEstates.clear()
                for (kenyaestateSnapshot in snapshot.children){
                    val upload = kenyaestateSnapshot.getValue(KenyaEstate::class.java)
                    upload!!.key = kenyaestateSnapshot.key
                    mKenyaEstates.add(upload)
                }
                kenyaestatelistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}