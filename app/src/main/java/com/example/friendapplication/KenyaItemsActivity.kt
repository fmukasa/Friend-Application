package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.friendapplication.adapter.KenyaListAdapter
import com.example.friendapplication.adapter.NigeriaListAdapter
import com.example.friendapplication.model.Kenya
import com.example.friendapplication.model.Nigeria
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class KenyaItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyans: MutableList<Kenya>
    private lateinit var kenyalistAdapter: KenyaListAdapter

    private lateinit var myDataLoaderProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_items)

        myDataLoaderProgressBar = findViewById(R.id.myDataLoaderProgressBar)
        mRecyclerView = findViewById(R.id.mRecyclerView)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyans = ArrayList()
        kenyalistAdapter = KenyaListAdapter(this@KenyaItemsActivity,mKenyans)
        mRecyclerView.adapter = kenyalistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("kenyans_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyans.clear()
                for (kenyaSnapshot in snapshot.children){
                    val upload = kenyaSnapshot.getValue(Kenya::class.java)
                    upload!!.key = kenyaSnapshot.key
                    mKenyans.add(upload)
                }
                kenyalistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}