package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaProducerListAdapter
import com.example.friendapplication.adapter.TanzaniaProducerListAdapter
import com.example.friendapplication.model.KenyaProducer
import com.example.friendapplication.model.TanzaniaProducer
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaProducerItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaProducers: MutableList<TanzaniaProducer>
    private lateinit var tanzaniaproducerlistAdapter: TanzaniaProducerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_producer_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaProducerItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaProducers = ArrayList()
        tanzaniaproducerlistAdapter = TanzaniaProducerListAdapter(
            this@TanzaniaProducerItemsActivity,mTanzaniaProducers)
        mRecyclerView.adapter = tanzaniaproducerlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("producer_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaProducerItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaProducers.clear()
                for (tanzaniaproducerSnapshot in snapshot.children){
                    val upload = tanzaniaproducerSnapshot.getValue(TanzaniaProducer::class.java)
                    upload!!.key = tanzaniaproducerSnapshot.key
                    mTanzaniaProducers.add(upload)
                }
                tanzaniaproducerlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}