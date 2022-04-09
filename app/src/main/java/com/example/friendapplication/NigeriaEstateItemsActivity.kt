package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.EstateListAdapter
import com.example.friendapplication.adapter.NigeriaEstateListAdapter
import com.example.friendapplication.model.Estate
import com.example.friendapplication.model.NigeriaEstate
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaEstateItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaEstates: MutableList<NigeriaEstate>
    private lateinit var nigeriaestatelistAdapter: NigeriaEstateListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_estate_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaEstateItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaEstates = ArrayList()
        nigeriaestatelistAdapter = NigeriaEstateListAdapter(
            this@NigeriaEstateItemsActivity,mNigeriaEstates)
        mRecyclerView.adapter = nigeriaestatelistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("estates_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaEstateItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaEstates.clear()
                for (nigeriaestateSnapshot in snapshot.children){
                    val upload = nigeriaestateSnapshot.getValue(NigeriaEstate::class.java)
                    upload!!.key = nigeriaestateSnapshot.key
                    mNigeriaEstates.add(upload)
                }
                nigeriaestatelistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}