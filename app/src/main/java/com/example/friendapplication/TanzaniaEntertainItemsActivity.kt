package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaEntertainListAdapter
import com.example.friendapplication.adapter.TanzaniaEntertainListAdapter
import com.example.friendapplication.model.KenyaEntertain
import com.example.friendapplication.model.TanzaniaEntertain
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaEntertainItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaEntertains: MutableList<TanzaniaEntertain>
    private lateinit var tanzaniaentertainlistAdapter: TanzaniaEntertainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_entertain_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaEntertainItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaEntertains = ArrayList()
        tanzaniaentertainlistAdapter = TanzaniaEntertainListAdapter(
            this@TanzaniaEntertainItemsActivity,mTanzaniaEntertains)
        mRecyclerView.adapter = tanzaniaentertainlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("entertain_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaEntertainItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaEntertains.clear()
                for (tanzaniaentertainSnapshot in snapshot.children){
                    val upload = tanzaniaentertainSnapshot.getValue(TanzaniaEntertain::class.java)
                    upload!!.key = tanzaniaentertainSnapshot.key
                    mTanzaniaEntertains.add(upload)
                }
                tanzaniaentertainlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}