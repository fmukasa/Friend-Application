package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaWriterListAdapter
import com.example.friendapplication.adapter.NigeriaWriterListAdapter
import com.example.friendapplication.model.KenyaWriter
import com.example.friendapplication.model.NigeriaWriter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class KenyaWriterItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaWriters: MutableList<KenyaWriter>
    private lateinit var kenyawriterlistAdapter: KenyaWriterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_writer_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaWriterItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaWriters = ArrayList()
        kenyawriterlistAdapter = KenyaWriterListAdapter(
            this@KenyaWriterItemsActivity,mKenyaWriters)
        mRecyclerView.adapter = kenyawriterlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("writers_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaWriterItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaWriters.clear()
                for (kenyawriterSnapshot in snapshot.children){
                    val upload = kenyawriterSnapshot.getValue(KenyaWriter::class.java)
                    upload!!.key = kenyawriterSnapshot.key
                    mKenyaWriters.add(upload)
                }
                kenyawriterlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}