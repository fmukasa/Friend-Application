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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import kotlinx.android.synthetic.main.activity_upload.*

class NigeriaWriterUploadActivity : AppCompatActivity() {

    private  var  mImageUri : Uri? = null
    private var mStorage: FirebaseStorage? = null
    private var mStorageRef : StorageReference? = null
    private var mDatabaseRef : DatabaseReference? = null
    private var mUploadTask : StorageTask<*>? = null
    private val PICK_IMAGE_REQUEST = 1

    private lateinit var button_choose_image_writerNa: Button
    private lateinit var upLoadBtnWriterNa : Button
    private lateinit var nameEditWriterNa: EditText
    private lateinit var descriptionEditWriterNa: EditText
    private lateinit var chooseImageViewWriterNa: ImageView
      private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_writer_upload)


        button_choose_image_writerNa = findViewById(R.id.button_choose_image_writerNa)
        upLoadBtnWriterNa = findViewById(R.id.upLoadBtnWriterNa)
        nameEditWriterNa = findViewById(R.id.nameEditWriterNa)
        descriptionEditWriterNa = findViewById(R.id.descriptionEditWriterNa)
        chooseImageViewWriterNa = findViewById(R.id.chooseImageViewWriterNa)
        progressBar = findViewById(R.id.progressBar)


        mStorage = FirebaseStorage.getInstance()
        mStorageRef = FirebaseStorage.getInstance().getReference("writers_na_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("writers_na_uploads")

        chooseImageViewWriterNa.setOnClickListener { openFileChooser() }
        button_choose_image_writerNa.setOnClickListener { openFileChooser() }
        upLoadBtnWriterNa.setOnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress) {
                Toast.makeText(this@NigeriaWriterUploadActivity,
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
            chooseImageViewWriterNa.setImageURI(mImageUri)
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
                    Toast.makeText(this@NigeriaWriterUploadActivity, "File Upload Successful", Toast.LENGTH_SHORT).show()
                    val upload = Decoration(
                        nameEditWriterNa.text.toString().trim { it <= ' ' },
                        mImageUri.toString(),
                        // descriptionEditText.text.toString().trim {it <= ' ' },
                        descriptionEditWriterNa.text.toString())
                    val uploadId = mDatabaseRef!!.push().key
                    mDatabaseRef!!.child(uploadId!!).setValue(upload)
                    progressBar.visibility = View.INVISIBLE
                    openImageActivity()
                }
                .addOnFailureListener { e->
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@NigeriaWriterUploadActivity, e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { tastSnpshot->
                    val mProgess = (100.0 * tastSnpshot.bytesTransferred/tastSnpshot.totalByteCount)
                    progressBar.progress = mProgess.toInt()
                }
        }else{
            Toast.makeText(this@NigeriaWriterUploadActivity,"You haven't selected any File", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openImageActivity() {
        startActivity(Intent(this@NigeriaWriterUploadActivity, NigeriaWriterActivity::class.java))
    }
}