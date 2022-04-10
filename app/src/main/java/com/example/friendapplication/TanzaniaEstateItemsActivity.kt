package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaEstateListAdapter
import com.example.friendapplication.adapter.TanzaniaEstateListAdapter
import com.example.friendapplication.model.KenyaEstate
import com.example.friendapplication.model.TanzaniaEstate
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaEstateItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaEstates: MutableList<TanzaniaEstate>
    private lateinit var tanzaniaestatelistAdapter: TanzaniaEstateListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_estate_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaEstateItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaEstates = ArrayList()
        tanzaniaestatelistAdapter = TanzaniaEstateListAdapter(
            this@TanzaniaEstateItemsActivity,mTanzaniaEstates)
        mRecyclerView.adapter = tanzaniaestatelistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("estates_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaEstateItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaEstates.clear()
                for (tanzaniaestateSnapshot in snapshot.children){
                    val upload = tanzaniaestateSnapshot.getValue(TanzaniaEstate::class.java)
                    upload!!.key = tanzaniaestateSnapshot.key
                    mTanzaniaEstates.add(upload)
                }
                tanzaniaestatelistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}