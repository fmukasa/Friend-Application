package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.NigeriaSoundListAdapter
import com.example.friendapplication.adapter.SoundListAdapter
import com.example.friendapplication.model.NigeriaSound
import com.example.friendapplication.model.Sound
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaSoundItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaSounds: MutableList<NigeriaSound>
    private lateinit var nigeriasoundlistAdapter: NigeriaSoundListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_sound_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaSoundItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaSounds = ArrayList()
        nigeriasoundlistAdapter = NigeriaSoundListAdapter(this@NigeriaSoundItemsActivity,mNigeriaSounds)
        mRecyclerView.adapter = nigeriasoundlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("sounds_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaSoundItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaSounds.clear()
                for (nigeriasoundSnapshot in snapshot.children){
                    val upload = nigeriasoundSnapshot.getValue(NigeriaSound::class.java)
                    upload!!.key = nigeriasoundSnapshot.key
                    mNigeriaSounds.add(upload)
                }
                nigeriasoundlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}
