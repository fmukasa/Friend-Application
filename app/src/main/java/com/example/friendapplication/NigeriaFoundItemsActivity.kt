package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.FoundListAdapter
import com.example.friendapplication.adapter.NigeriaFoundListAdapter
import com.example.friendapplication.model.Found
import com.example.friendapplication.model.NigeriaFound
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaFoundItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaFounds: MutableList<NigeriaFound>
    private lateinit var nigeriafoundlistAdapter: NigeriaFoundListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_found_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaFoundItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaFounds = ArrayList()
        nigeriafoundlistAdapter = NigeriaFoundListAdapter(this@NigeriaFoundItemsActivity,mNigeriaFounds)
        mRecyclerView.adapter = nigeriafoundlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("founds_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaFoundItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaFounds.clear()
                for (nigeriafoundSnapshot in snapshot.children){
                    val upload = nigeriafoundSnapshot.getValue(NigeriaFound::class.java)
                    upload!!.key = nigeriafoundSnapshot.key
                    mNigeriaFounds.add(upload)
                }
                nigeriafoundlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}