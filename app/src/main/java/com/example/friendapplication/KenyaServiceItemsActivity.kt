package com.example.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendapplication.adapter.KenyaServiceListAdapter
import com.example.friendapplication.adapter.NigeriaServiceListAdapter
import com.example.friendapplication.model.KenyaService
import com.example.friendapplication.model.NigeriaService
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class KenyaServiceItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaServices: MutableList<KenyaService>
    private lateinit var kenyaservicelistAdapter: KenyaServiceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_service_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaServiceItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaServices = ArrayList()
        kenyaservicelistAdapter = KenyaServiceListAdapter(
            this@KenyaServiceItemsActivity,mKenyaServices)
        mRecyclerView.adapter = kenyaservicelistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("services_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaServiceItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaServices.clear()
                for (kenyaserviceSnapshot in snapshot.children){
                    val upload = kenyaserviceSnapshot.getValue(KenyaService::class.java)
                    upload!!.key = kenyaserviceSnapshot.key
                    mKenyaServices.add(upload)
                }
                kenyaservicelistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}