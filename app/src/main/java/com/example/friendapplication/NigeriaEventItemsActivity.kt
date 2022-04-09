package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.EventListAdapter
import com.example.friendapplication.adapter.NigeriaEventListAdapter
import com.example.friendapplication.model.Event
import com.example.friendapplication.model.NigeriaEvent
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaEventItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaEvents: MutableList<NigeriaEvent>
    private lateinit var nigeriaeventlistAdapter: NigeriaEventListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_event_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaEventItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaEvents = ArrayList()
        nigeriaeventlistAdapter = NigeriaEventListAdapter(this@NigeriaEventItemsActivity,mNigeriaEvents)
        mRecyclerView.adapter = nigeriaeventlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("events_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaEventItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaEvents.clear()
                for (nigeriaeventSnapshot in snapshot.children){
                    val upload = nigeriaeventSnapshot.getValue(NigeriaEvent::class.java)
                    upload!!.key = nigeriaeventSnapshot.key
                    mNigeriaEvents.add(upload)
                }
                nigeriaeventlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}