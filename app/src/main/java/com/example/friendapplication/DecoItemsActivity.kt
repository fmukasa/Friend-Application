package com.example.friendapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.friendapplication.adapter.DecoListAdapter
import com.example.friendapplication.adapter.ListAdapter
import com.example.friendapplication.model.Decoration
import com.example.friendapplication.model.Teacher
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*


class DecoItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mDecorations: MutableList<Decoration>
    private lateinit var decolistAdapter: DecoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deco_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@DecoItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mDecorations = ArrayList()
        decolistAdapter = DecoListAdapter(this@DecoItemsActivity,mDecorations)
        mRecyclerView.adapter = decolistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("decorations_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DecoItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mDecorations.clear()
                for (decorationSnapshot in snapshot.children){
                    val upload = decorationSnapshot.getValue(Decoration::class.java)
                    upload!!.key = decorationSnapshot.key
                    mDecorations.add(upload)
                }
                decolistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}




