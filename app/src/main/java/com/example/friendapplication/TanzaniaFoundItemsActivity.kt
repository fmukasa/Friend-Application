package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaFoundListAdapter
import com.example.friendapplication.adapter.TanzaniaFoundListAdapter
import com.example.friendapplication.model.KenyaFound
import com.example.friendapplication.model.TanzaniaFound
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaFoundItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaFounds: MutableList<TanzaniaFound>
    private lateinit var tanzaniafoundlistAdapter: TanzaniaFoundListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_found_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaFoundItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaFounds = ArrayList()
        tanzaniafoundlistAdapter = TanzaniaFoundListAdapter(this@TanzaniaFoundItemsActivity,mTanzaniaFounds)
        mRecyclerView.adapter = tanzaniafoundlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("founds_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaFoundItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaFounds.clear()
                for (tanzaniafoundSnapshot in snapshot.children){
                    val upload = tanzaniafoundSnapshot.getValue(TanzaniaFound::class.java)
                    upload!!.key = tanzaniafoundSnapshot.key
                    mTanzaniaFounds.add(upload)
                }
                tanzaniafoundlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}