package events.app.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import events.app.friendapplication.adapter.DirectorListAdapter
import events.app.friendapplication.model.Director
import kotlinx.android.synthetic.main.activity_items.*

class DirectorItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mDirectors: MutableList<Director>
    private lateinit var directorlistAdapter: DirectorListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_director_items)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@DirectorItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mDirectors = ArrayList()
       directorlistAdapter = DirectorListAdapter(this@DirectorItemsActivity,mDirectors)
        mRecyclerView.adapter = directorlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("director_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DirectorItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
               mDirectors.clear()
                for (directorSnapshot in snapshot.children){
                    val upload = directorSnapshot.getValue(Director::class.java)
                    upload!!.key = directorSnapshot.key
                   mDirectors.add(upload)
                }
               directorlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}