package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaSoundListAdapter
import com.example.friendapplication.adapter.NigeriaSoundListAdapter
import com.example.friendapplication.model.KenyaSound
import com.example.friendapplication.model.NigeriaSound
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class KenyaSoundItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaSounds: MutableList<KenyaSound>
    private lateinit var kenyasoundlistAdapter: KenyaSoundListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_sound_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaSoundItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaSounds = ArrayList()
        kenyasoundlistAdapter = KenyaSoundListAdapter(this@KenyaSoundItemsActivity,mKenyaSounds)
        mRecyclerView.adapter = kenyasoundlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("sounds_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaSoundItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaSounds.clear()
                for (kenyasoundSnapshot in snapshot.children){
                    val upload = kenyasoundSnapshot.getValue(KenyaSound::class.java)
                    upload!!.key = kenyasoundSnapshot.key
                    mKenyaSounds.add(upload)
                }
                kenyasoundlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}
