package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaProducerListAdapter
import com.example.friendapplication.adapter.NigeriaProducerListAdapter
import com.example.friendapplication.model.KenyaProducer
import com.example.friendapplication.model.NigeriaProducer
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class KenyaProducerItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaProducers: MutableList<KenyaProducer>
    private lateinit var kenyaproducerlistAdapter: KenyaProducerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_producer_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaProducerItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaProducers = ArrayList()
        kenyaproducerlistAdapter = KenyaProducerListAdapter(
            this@KenyaProducerItemsActivity,mKenyaProducers)
        mRecyclerView.adapter = kenyaproducerlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("producer_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaProducerItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaProducers.clear()
                for (kenyaproducerSnapshot in snapshot.children){
                    val upload = kenyaproducerSnapshot.getValue(KenyaProducer::class.java)
                    upload!!.key = kenyaproducerSnapshot.key
                    mKenyaProducers.add(upload)
                }
                kenyaproducerlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}