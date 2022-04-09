package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.NigeriaProducerListAdapter
import com.example.friendapplication.adapter.ProducerListAdapter
import com.example.friendapplication.model.NigeriaProducer
import com.example.friendapplication.model.Producer
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaProducerItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaProducers: MutableList<NigeriaProducer>
    private lateinit var nigeriaproducerlistAdapter: NigeriaProducerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_producer_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaProducerItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaProducers = ArrayList()
        nigeriaproducerlistAdapter = NigeriaProducerListAdapter(
            this@NigeriaProducerItemsActivity,mNigeriaProducers)
        mRecyclerView.adapter = nigeriaproducerlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("producer_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaProducerItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaProducers.clear()
                for (nigeriaproducerSnapshot in snapshot.children){
                    val upload = nigeriaproducerSnapshot.getValue(NigeriaProducer::class.java)
                    upload!!.key = nigeriaproducerSnapshot.key
                    mNigeriaProducers.add(upload)
                }
                nigeriaproducerlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}