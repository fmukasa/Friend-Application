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
import com.example.friendapplication.adapter.TanzaniaDecorationListAdapter
import com.example.friendapplication.model.KenyaDecoration
import com.example.friendapplication.model.TanzaniaDecoration
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class TanzaniaDecorationItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaDecorations: MutableList<TanzaniaDecoration>
    private lateinit var tanzaniadecorationlistAdapter: TanzaniaDecorationListAdapter

    private lateinit var myDataLoaderProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_decoration_items)


        myDataLoaderProgressBar = findViewById(R.id.myDataLoaderProgressBar)
        mRecyclerView = findViewById(R.id.mRecyclerView)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaDecorationItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaDecorations = ArrayList()
        tanzaniadecorationlistAdapter = TanzaniaDecorationListAdapter(
            this@TanzaniaDecorationItemsActivity,mTanzaniaDecorations)
        mRecyclerView.adapter = tanzaniadecorationlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("tanzanians_deco_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaDecorationItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaDecorations.clear()
                for (tanzaniadecorationSnapshot in snapshot.children){
                    val upload = tanzaniadecorationSnapshot.getValue(TanzaniaDecoration::class.java)
                    upload!!.key = tanzaniadecorationSnapshot.key
                    mTanzaniaDecorations.add(upload)
                }
                tanzaniadecorationlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}

