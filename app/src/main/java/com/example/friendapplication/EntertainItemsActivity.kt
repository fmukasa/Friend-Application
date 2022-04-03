package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.EntertainListAdapter
import com.example.friendapplication.adapter.PromoterListAdapter
import com.example.friendapplication.model.Entertain
import com.example.friendapplication.model.Promoter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class EntertainItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mEntertains: MutableList<Entertain>
    private lateinit var entertainlistAdapter: EntertainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entertain_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@EntertainItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mEntertains = ArrayList()
        entertainlistAdapter = EntertainListAdapter(this@EntertainItemsActivity,mEntertains)
        mRecyclerView.adapter = entertainlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("entertain_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EntertainItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mEntertains.clear()
                for (entertainSnapshot in snapshot.children){
                    val upload = entertainSnapshot.getValue(Entertain::class.java)
                    upload!!.key = entertainSnapshot.key
                    mEntertains.add(upload)
                }
                entertainlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}