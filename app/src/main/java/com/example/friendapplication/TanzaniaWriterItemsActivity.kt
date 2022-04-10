package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaWriterListAdapter
import com.example.friendapplication.adapter.TanzaniaWriterListAdapter
import com.example.friendapplication.model.KenyaWriter
import com.example.friendapplication.model.TanzaniaWriter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaWriterItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaWriters: MutableList<TanzaniaWriter>
    private lateinit var tanzaniawriterlistAdapter: TanzaniaWriterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_writer_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaWriterItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaWriters = ArrayList()
        tanzaniawriterlistAdapter = TanzaniaWriterListAdapter(
            this@TanzaniaWriterItemsActivity,mTanzaniaWriters)
        mRecyclerView.adapter = tanzaniawriterlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("writers_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaWriterItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaWriters.clear()
                for (tanzaniawriterSnapshot in snapshot.children){
                    val upload = tanzaniawriterSnapshot.getValue(TanzaniaWriter::class.java)
                    upload!!.key = tanzaniawriterSnapshot.key
                    mTanzaniaWriters.add(upload)
                }
                tanzaniawriterlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}