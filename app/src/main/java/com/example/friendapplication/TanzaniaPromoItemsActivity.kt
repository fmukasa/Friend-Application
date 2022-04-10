package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaPromoterListAdapter
import com.example.friendapplication.adapter.TanzaniaPromoterListAdapter
import com.example.friendapplication.model.KenyaPromoter
import com.example.friendapplication.model.TanzaniaPromoter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaPromoItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaPromots: MutableList<TanzaniaPromoter>
    private lateinit var tanzaniapromoterlistAdapter: TanzaniaPromoterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_promo_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaPromoItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
       mTanzaniaPromots = ArrayList()
        tanzaniapromoterlistAdapter = TanzaniaPromoterListAdapter(
            this@TanzaniaPromoItemsActivity,mTanzaniaPromots)
        mRecyclerView.adapter = tanzaniapromoterlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("promoters_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaPromoItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaPromots.clear()
                for (tanzaniapromoterSnapshot in snapshot.children){
                    val upload = tanzaniapromoterSnapshot.getValue(TanzaniaPromoter::class.java)
                    upload!!.key = tanzaniapromoterSnapshot.key
                    mTanzaniaPromots.add(upload)
                }
                tanzaniapromoterlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}