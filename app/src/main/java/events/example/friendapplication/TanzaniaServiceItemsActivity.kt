package events.app.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import events.app.friendapplication.adapter.TanzaniaServiceListAdapter
import events.app.friendapplication.model.TanzaniaService
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaServiceItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaServices: MutableList<TanzaniaService>
    private lateinit var tanzaniaservicelistAdapter: TanzaniaServiceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_service_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaServiceItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaServices = ArrayList()
        tanzaniaservicelistAdapter = TanzaniaServiceListAdapter(
            this@TanzaniaServiceItemsActivity,mTanzaniaServices)
        mRecyclerView.adapter = tanzaniaservicelistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("services_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaServiceItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaServices.clear()
                for (tanzaniaserviceSnapshot in snapshot.children){
                    val upload = tanzaniaserviceSnapshot.getValue(TanzaniaService::class.java)
                    upload!!.key = tanzaniaserviceSnapshot.key
                    mTanzaniaServices.add(upload)
                }
                tanzaniaservicelistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}