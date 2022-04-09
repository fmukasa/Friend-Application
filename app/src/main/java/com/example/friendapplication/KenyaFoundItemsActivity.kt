package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaFoundListAdapter
import com.example.friendapplication.adapter.NigeriaFoundListAdapter
import com.example.friendapplication.model.KenyaFound
import com.example.friendapplication.model.NigeriaFound
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class KenyaFoundItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaFounds: MutableList<KenyaFound>
    private lateinit var kenyafoundlistAdapter: KenyaFoundListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_found_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaFoundItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaFounds = ArrayList()
        kenyafoundlistAdapter = KenyaFoundListAdapter(this@KenyaFoundItemsActivity,mKenyaFounds)
        mRecyclerView.adapter = kenyafoundlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("founds_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaFoundItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaFounds.clear()
                for (kenyafoundSnapshot in snapshot.children){
                    val upload = kenyafoundSnapshot.getValue(KenyaFound::class.java)
                    upload!!.key = kenyafoundSnapshot.key
                    mKenyaFounds.add(upload)
                }
                kenyafoundlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}