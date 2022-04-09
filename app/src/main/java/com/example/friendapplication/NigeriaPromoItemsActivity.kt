package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.NigeriaPromoterListAdapter
import com.example.friendapplication.adapter.PromoterListAdapter
import com.example.friendapplication.model.NigeriaPromoter
import com.example.friendapplication.model.Promoter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaPromoItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaPromots: MutableList<NigeriaPromoter>
    private lateinit var nigeriapromoterlistAdapter: NigeriaPromoterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_promo_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaPromoItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaPromots = ArrayList()
        nigeriapromoterlistAdapter = NigeriaPromoterListAdapter(
            this@NigeriaPromoItemsActivity,mNigeriaPromots)
        mRecyclerView.adapter = nigeriapromoterlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("promoters_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaPromoItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaPromots.clear()
                for (nigeriapromoterSnapshot in snapshot.children){
                    val upload = nigeriapromoterSnapshot.getValue(NigeriaPromoter::class.java)
                    upload!!.key = nigeriapromoterSnapshot.key
                    mNigeriaPromots.add(upload)
                }
                nigeriapromoterlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}