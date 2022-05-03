package events.app.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import events.app.friendapplication.adapter.KenyaEventListAdapter
import events.app.friendapplication.model.KenyaEvent
import kotlinx.android.synthetic.main.activity_items.*

class KenyaEventItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaEvents: MutableList<KenyaEvent>
    private lateinit var kenyaeventlistAdapter: KenyaEventListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_event_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaEventItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaEvents = ArrayList()
        kenyaeventlistAdapter = KenyaEventListAdapter(this@KenyaEventItemsActivity,mKenyaEvents)
        mRecyclerView.adapter = kenyaeventlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("events_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaEventItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaEvents.clear()
                for (kenyaeventSnapshot in snapshot.children){
                    val upload = kenyaeventSnapshot.getValue(KenyaEvent::class.java)
                    upload!!.key = kenyaeventSnapshot.key
                    mKenyaEvents.add(upload)
                }
                kenyaeventlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}