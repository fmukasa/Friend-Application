package events.app.friendapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import events.app.friendapplication.adapter.KenyaDirectorListAdapter
import events.app.friendapplication.model.KenyaDirector
import kotlinx.android.synthetic.main.activity_items.*

class KenyaDirectorItemsActivity : AppCompatActivity() {


    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mKenyaDirectors: MutableList<KenyaDirector>
    private lateinit var kenyadirectorlistAdapter: KenyaDirectorListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_director_items)


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@KenyaDirectorItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mKenyaDirectors = ArrayList()
        kenyadirectorlistAdapter = KenyaDirectorListAdapter(
            this@KenyaDirectorItemsActivity,mKenyaDirectors)
        mRecyclerView.adapter = kenyadirectorlistAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("director_ke_uploads")
        mDBListener = mDatabaseRef!!.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@KenyaDirectorItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mKenyaDirectors.clear()
                for (kenyadirectorSnapshot in snapshot.children){
                    val upload = kenyadirectorSnapshot.getValue(KenyaDirector::class.java)
                    upload!!.key = kenyadirectorSnapshot.key
                    mKenyaDirectors.add(upload)
                }
                kenyadirectorlistAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        }).also { mDBListener = it }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}