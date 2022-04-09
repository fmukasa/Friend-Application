package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.friendapplication.adapter.KenyaDecorationListAdapter
import com.example.friendapplication.adapter.NigeriaDecorationListAdapter
import com.example.friendapplication.model.KenyaDecoration
import com.example.friendapplication.model.NigeriaDecoration
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class KenyaDecorationItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaDecorations: MutableList<KenyaDecoration>
    private lateinit var kenyadecorationlistAdapter: KenyaDecorationListAdapter

    private lateinit var myDataLoaderProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_decoration_items)


        myDataLoaderProgressBar = findViewById(R.id.myDataLoaderProgressBar)
        mRecyclerView = findViewById(R.id.mRecyclerView)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaDecorationItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaDecorations = ArrayList()
        kenyadecorationlistAdapter = KenyaDecorationListAdapter(
            this@KenyaDecorationItemsActivity,mKenyaDecorations)
        mRecyclerView.adapter = kenyadecorationlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("kenyans_deco_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaDecorationItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaDecorations.clear()
                for (kenyadecorationSnapshot in snapshot.children){
                    val upload = kenyadecorationSnapshot.getValue(KenyaDecoration::class.java)
                    upload!!.key = kenyadecorationSnapshot.key
                    mKenyaDecorations.add(upload)
                }
                kenyadecorationlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}

