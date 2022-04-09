package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaEntertainListAdapter
import com.example.friendapplication.adapter.NigeriaEntertainListAdapter
import com.example.friendapplication.model.KenyaEntertain
import com.example.friendapplication.model.NigeriaEntertain
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class KenyaEntertainItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaEntertains: MutableList<KenyaEntertain>
    private lateinit var kenyaentertainlistAdapter: KenyaEntertainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_entertain_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaEntertainItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaEntertains = ArrayList()
        kenyaentertainlistAdapter = KenyaEntertainListAdapter(
            this@KenyaEntertainItemsActivity,mKenyaEntertains)
        mRecyclerView.adapter = kenyaentertainlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("entertain_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaEntertainItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaEntertains.clear()
                for (kenyaentertainSnapshot in snapshot.children){
                    val upload = kenyaentertainSnapshot.getValue(KenyaEntertain::class.java)
                    upload!!.key = kenyaentertainSnapshot.key
                    mKenyaEntertains.add(upload)
                }
                kenyaentertainlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}