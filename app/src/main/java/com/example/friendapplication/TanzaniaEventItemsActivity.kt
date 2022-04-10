package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaEventListAdapter
import com.example.friendapplication.adapter.TanzaniaEventListAdapter
import com.example.friendapplication.model.KenyaEvent
import com.example.friendapplication.model.TanzaniaEvent
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaEventItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaEvents: MutableList<TanzaniaEvent>
    private lateinit var tanzaniaeventlistAdapter: TanzaniaEventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_event_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaEventItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaEvents = ArrayList()
        tanzaniaeventlistAdapter = TanzaniaEventListAdapter(this@TanzaniaEventItemsActivity,mTanzaniaEvents)
        mRecyclerView.adapter = tanzaniaeventlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("events_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaEventItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaEvents.clear()
                for (tanzaniaeventSnapshot in snapshot.children){
                    val upload = tanzaniaeventSnapshot.getValue(TanzaniaEvent::class.java)
                    upload!!.key = tanzaniaeventSnapshot.key
                    mTanzaniaEvents.add(upload)
                }
                tanzaniaeventlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}