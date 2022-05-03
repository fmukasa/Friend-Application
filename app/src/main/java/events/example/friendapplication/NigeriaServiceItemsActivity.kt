package events.app.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import events.app.friendapplication.adapter.NigeriaServiceListAdapter
import events.app.friendapplication.model.NigeriaService
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaServiceItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaServices: MutableList<NigeriaService>
    private lateinit var nigeriaservicelistAdapter: NigeriaServiceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_service_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaServiceItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaServices = ArrayList()
        nigeriaservicelistAdapter = NigeriaServiceListAdapter(
            this@NigeriaServiceItemsActivity,mNigeriaServices)
        mRecyclerView.adapter = nigeriaservicelistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("services_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaServiceItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaServices.clear()
                for (nigeriaserviceSnapshot in snapshot.children){
                    val upload = nigeriaserviceSnapshot.getValue(NigeriaService::class.java)
                    upload!!.key = nigeriaserviceSnapshot.key
                    mNigeriaServices.add(upload)
                }
                nigeriaservicelistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}