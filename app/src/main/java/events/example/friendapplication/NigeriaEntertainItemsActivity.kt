package events.app.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import events.app.friendapplication.adapter.NigeriaEntertainListAdapter
import events.app.friendapplication.model.NigeriaEntertain
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaEntertainItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaEntertains: MutableList<NigeriaEntertain>
    private lateinit var nigeriaentertainlistAdapter: NigeriaEntertainListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_entertain_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaEntertainItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaEntertains = ArrayList()
        nigeriaentertainlistAdapter = NigeriaEntertainListAdapter(
            this@NigeriaEntertainItemsActivity,mNigeriaEntertains)
        mRecyclerView.adapter = nigeriaentertainlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("entertain_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaEntertainItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaEntertains.clear()
                for (nigeriaentertainSnapshot in snapshot.children){
                    val upload = nigeriaentertainSnapshot.getValue(NigeriaEntertain::class.java)
                    upload!!.key = nigeriaentertainSnapshot.key
                    mNigeriaEntertains.add(upload)
                }
                nigeriaentertainlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}