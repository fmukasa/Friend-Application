package events.app.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import events.app.friendapplication.adapter.TanzaniaDirectorListAdapter
import events.app.friendapplication.model.TanzaniaDirector
import kotlinx.android.synthetic.main.activity_items.*

class TanzaniaDirectorItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mTanzaniaDirectors: MutableList<TanzaniaDirector>
    private lateinit var tanzaniadirectorlistAdapter: TanzaniaDirectorListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_director_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanzaniaDirectorItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanzaniaDirectors = ArrayList()
        tanzaniadirectorlistAdapter = TanzaniaDirectorListAdapter(
            this@TanzaniaDirectorItemsActivity,mTanzaniaDirectors)
        mRecyclerView.adapter = tanzaniadirectorlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("director_tz_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanzaniaDirectorItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanzaniaDirectors.clear()
                for (tanzaniadirectorSnapshot in snapshot.children){
                    val upload = tanzaniadirectorSnapshot.getValue(TanzaniaDirector::class.java)
                    upload!!.key = tanzaniadirectorSnapshot.key
                    mTanzaniaDirectors.add(upload)
                }
                tanzaniadirectorlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}