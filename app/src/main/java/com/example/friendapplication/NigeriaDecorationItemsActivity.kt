package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.friendapplication.adapter.DecoListAdapter
import com.example.friendapplication.adapter.NigeriaDecorationListAdapter
import com.example.friendapplication.model.Decoration
import com.example.friendapplication.model.NigeriaDecoration
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaDecorationItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaDecorations: MutableList<NigeriaDecoration>
    private lateinit var nigeriadecorationlistAdapter: NigeriaDecorationListAdapter

    private lateinit var myDataLoaderProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_decoration_items)


        myDataLoaderProgressBar = findViewById(R.id.myDataLoaderProgressBar)
        mRecyclerView = findViewById(R.id.mRecyclerView)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaDecorationItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaDecorations = ArrayList()
        nigeriadecorationlistAdapter = NigeriaDecorationListAdapter(
            this@NigeriaDecorationItemsActivity,mNigeriaDecorations)
        mRecyclerView.adapter = nigeriadecorationlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("nigerians_deco_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaDecorationItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaDecorations.clear()
                for (nigeriadecorationSnapshot in snapshot.children){
                    val upload = nigeriadecorationSnapshot.getValue(NigeriaDecoration::class.java)
                    upload!!.key = nigeriadecorationSnapshot.key
                    mNigeriaDecorations.add(upload)
                }
                nigeriadecorationlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}

