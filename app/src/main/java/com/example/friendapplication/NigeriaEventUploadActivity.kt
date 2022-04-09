package com.example.friendapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import com.example.friendapplication.model.Decoration
import com.example.friendapplication.model.NigeriaEvent
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import kotlinx.android.synthetic.main.activity_upload.*

class NigeriaEventUploadActivity : AppCompatActivity() {

    private  var  mImageUri : Uri? = null
    private var mStorage: FirebaseStorage? = null
    private var mStorageRef : StorageReference? = null
    private var mDatabaseRef : DatabaseReference? = null
    private var mUploadTask : StorageTask<*>? = null
    private val PICK_IMAGE_REQUEST = 1

    private lateinit var button_choose_image_eventNa: Button
    private lateinit var upLoadBtnEventNa : Button
    private lateinit var nameEditEventNa: EditText
    private lateinit var descriptionEditEventNa: EditText
    private lateinit var chooseImageViewEventNa: ImageView
      private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_event_upload)

        button_choose_image_eventNa = findViewById(R.id.button_choose_image_eventNa)
        upLoadBtnEventNa = findViewById(R.id.upLoadBtnEventNa)
        nameEditEventNa = findViewById(R.id.nameEditEventNa)
        descriptionEditEventNa = findViewById(R.id.descriptionEditEventNa)
        chooseImageViewEventNa = findViewById(R.id.chooseImageViewEventNa)
        progressBar = findViewById(R.id.progressBar)

        mStorage = FirebaseStorage.getInstance()
        mStorageRef = FirebaseStorage.getInstance().getReference("events_na_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("events_na_uploads")

        chooseImageViewEventNa.setOnClickListener { openFileChooser() }
        button_choose_image_eventNa.setOnClickListener { openFileChooser() }
        upLoadBtnEventNa.setOnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress) {
                Toast.makeText(this@NigeriaEventUploadActivity,
                    "An Upload is still in Progress", Toast.LENGTH_SHORT).show()
            } else {
                uploadFile()
            }

            /** val getaction = registerForActivityResult(
            ActivityResultContracts.GetContent().
            ActivityResultCallback {uri ->
            chooseImageView.setImageURI(uri)

            })*/
        }
    }
    private fun openFileChooser() {
        //getaction.launch("image/*")
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
            && data != null && data.data != null){
            mImageUri = data.data
            chooseImageViewEventNa.setImageURI(mImageUri)
        }
    }
    private fun getFileExtension(uri : Uri):String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }

    private fun uploadFile() {
        if (mImageUri != null){
            val fileReference = mStorageRef!!.child(
                System.currentTimeMillis().toString() +"."+ getFileExtension(mImageUri!!))
            progressBar.visibility = View.VISIBLE
            progressBar.isIndeterminate = true
            mUploadTask = fileReference.putFile(mImageUri!!)
                .addOnSuccessListener {
                    val handler = Handler()
                    handler.postDelayed(
                        {
                            progressBar.visibility = View.VISIBLE
                            progressBar.isIndeterminate = false
                            progressBar.progress = 0
                        }
                        ,500)
                    Toast.makeText(this@NigeriaEventUploadActivity, "File Upload Successful", Toast.LENGTH_SHORT).show()
                    val upload = NigeriaEvent(
                        nameEditEventNa.text.toString().trim { it <= ' ' },
                        mImageUri.toString(),
                        // descriptionEditText.text.toString().trim {it <= ' ' },
                        descriptionEditEventNa.text.toString())
                    val uploadId = mDatabaseRef!!.push().key
                    mDatabaseRef!!.child(uploadId!!).setValue(upload)
                    progressBar.visibility = View.INVISIBLE
                    openImageActivity()
                }
                .addOnFailureListener { e->
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@NigeriaEventUploadActivity, e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { tastSnpshot->
                    val mProgess = (100.0 * tastSnpshot.bytesTransferred/tastSnpshot.totalByteCount)
                    progressBar.progress = mProgess.toInt()
                }
        }else{
            Toast.makeText(this@NigeriaEventUploadActivity,"You haven't selected any File", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openImageActivity() {
        startActivity(Intent(this@NigeriaEventUploadActivity, NigeriaEventActivity::class.java))
    }
}
