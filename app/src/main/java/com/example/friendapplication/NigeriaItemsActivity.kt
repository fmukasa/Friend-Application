package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.friendapplication.adapter.FoundListAdapter
import com.example.friendapplication.adapter.NigeriaListAdapter
import com.example.friendapplication.model.Found
import com.example.friendapplication.model.Nigeria
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigerians: MutableList<Nigeria>
    private lateinit var nigerialistAdapter: NigeriaListAdapter

    private lateinit var myDataLoaderProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_items)

      myDataLoaderProgressBar = findViewById(R.id.myDataLoaderProgressBar)
        mRecyclerView = findViewById(R.id.mRecyclerView)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigerians = ArrayList()
        nigerialistAdapter = NigeriaListAdapter(this@NigeriaItemsActivity,mNigerians)
        mRecyclerView.adapter = nigerialistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("nigerians_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigerians.clear()
                for (nigeriaSnapshot in snapshot.children){
                    val upload = nigeriaSnapshot.getValue(Nigeria::class.java)
                    upload!!.key = nigeriaSnapshot.key
                    mNigerians.add(upload)
                }
                nigerialistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}