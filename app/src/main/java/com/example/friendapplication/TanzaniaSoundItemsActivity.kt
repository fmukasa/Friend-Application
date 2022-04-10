package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaSoundListAdapter
import com.example.friendapplication.adapter.TanzaniaSoundListAdapter
import com.example.friendapplication.model.KenyaSound
import com.example.friendapplication.model.TanzaniaSound
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaSoundItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaSounds: MutableList<TanzaniaSound>
    private lateinit var tanzaniasoundlistAdapter: TanzaniaSoundListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_sound_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaSoundItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaSounds = ArrayList()
        tanzaniasoundlistAdapter = TanzaniaSoundListAdapter(this@TanzaniaSoundItemsActivity,mTanzaniaSounds)
        mRecyclerView.adapter = tanzaniasoundlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("sounds_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaSoundItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaSounds.clear()
                for (tanzaniasoundSnapshot in snapshot.children){
                    val upload = tanzaniasoundSnapshot.getValue(TanzaniaSound::class.java)
                    upload!!.key = tanzaniasoundSnapshot.key
                    mTanzaniaSounds.add(upload)
                }
                tanzaniasoundlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}
