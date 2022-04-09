package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaPromoterListAdapter
import com.example.friendapplication.adapter.NigeriaPromoterListAdapter
import com.example.friendapplication.model.KenyaPromoter
import com.example.friendapplication.model.NigeriaPromoter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class KenyaPromoItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaPromots: MutableList<KenyaPromoter>
    private lateinit var kenyapromoterlistAdapter: KenyaPromoterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_promo_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaPromoItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaPromots = ArrayList()
        kenyapromoterlistAdapter = KenyaPromoterListAdapter(
            this@KenyaPromoItemsActivity,mKenyaPromots)
        mRecyclerView.adapter = kenyapromoterlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("promoters_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaPromoItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaPromots.clear()
                for (kenyapromoterSnapshot in snapshot.children){
                    val upload = kenyapromoterSnapshot.getValue(KenyaPromoter::class.java)
                    upload!!.key = kenyapromoterSnapshot.key
                    mKenyaPromots.add(upload)
                }
                kenyapromoterlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}