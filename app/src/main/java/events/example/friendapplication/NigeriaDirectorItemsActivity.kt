package events.app.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import events.app.friendapplication.adapter.NigeriaDirectorListAdapter
import events.app.friendapplication.model.NigeriaDirector
import kotlinx.android.synthetic.main.activity_items.*

class NigeriaDirectorItemsActivity : AppCompatActivity() {

    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mNigeriaDirectors: MutableList<NigeriaDirector>
    private lateinit var nigeriadirectorlistAdapter: NigeriaDirectorListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_director_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@NigeriaDirectorItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mNigeriaDirectors = ArrayList()
        nigeriadirectorlistAdapter = NigeriaDirectorListAdapter(
            this@NigeriaDirectorItemsActivity,mNigeriaDirectors)
        mRecyclerView.adapter = nigeriadirectorlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("director_na_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NigeriaDirectorItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mNigeriaDirectors.clear()
                for (nigeriadirectorSnapshot in snapshot.children){
                    val upload = nigeriadirectorSnapshot.getValue(NigeriaDirector::class.java)
                    upload!!.key = nigeriadirectorSnapshot.key
                    mNigeriaDirectors.add(upload)
                }
                nigeriadirectorlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}