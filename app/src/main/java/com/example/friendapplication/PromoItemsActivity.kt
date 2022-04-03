package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.PromoterListAdapter
import com.example.friendapplication.adapter.SoundListAdapter
import com.example.friendapplication.model.Promoter
import com.example.friendapplication.model.Sound
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class PromoItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mPromots: MutableList<Promoter>
    private lateinit var promoterlistAdapter: PromoterListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@PromoItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mPromots = ArrayList()
        promoterlistAdapter = PromoterListAdapter(this@PromoItemsActivity,mPromots)
        mRecyclerView.adapter = promoterlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("promoters_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@PromoItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mPromots.clear()
                for (promoterSnapshot in snapshot.children){
                    val upload = promoterSnapshot.getValue(Promoter::class.java)
                    upload!!.key = promoterSnapshot.key
                    mPromots.add(upload)
                }
                promoterlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}
