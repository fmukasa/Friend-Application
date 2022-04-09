package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.NigeriaWriterListAdapter
import com.example.friendapplication.adapter.WriterListAdapter
import com.example.friendapplication.model.NigeriaWriter
import com.example.friendapplication.model.Writer
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaWriterItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaWriters: MutableList<NigeriaWriter>
    private lateinit var nigeriawriterlistAdapter: NigeriaWriterListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_writer_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaWriterItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaWriters = ArrayList()
        nigeriawriterlistAdapter = NigeriaWriterListAdapter(
            this@NigeriaWriterItemsActivity,mNigeriaWriters)
        mRecyclerView.adapter = nigeriawriterlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("writers_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaWriterItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaWriters.clear()
                for (nigeriawriterSnapshot in snapshot.children){
                    val upload = nigeriawriterSnapshot.getValue(NigeriaWriter::class.java)
                    upload!!.key = nigeriawriterSnapshot.key
                    mNigeriaWriters.add(upload)
                }
                nigeriawriterlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}