package com.example.friendapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import com.example.friendapplication.model.Kenya
import com.example.friendapplication.model.Nigeria
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask

class KenyaUploadActivity : AppCompatActivity() {
    private  var  mImageUri : Uri? = null
    private var mStorage: FirebaseStorage? = null
    private var mStorageRef : StorageReference? = null
    private var mDatabaseRef : DatabaseReference? = null
    private var mUploadTask : StorageTask<*>? = null
    private val PICK_IMAGE_REQUEST = 1

    private lateinit var button_choose_image_kenya: Button
    private lateinit var upLoadBtnKenya : Button
    private lateinit var nameEditKenya: EditText
    private lateinit var descriptionEditKenya: EditText
    private lateinit var chooseImageViewKenya: ImageView
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_upload)


        button_choose_image_kenya = findViewById(R.id.button_choose_image_kenya)
        upLoadBtnKenya = findViewById(R.id.upLoadBtnKenya)
        nameEditKenya = findViewById(R.id.nameEditKenya)
        descriptionEditKenya = findViewById(R.id.descriptionEditKenya)
        chooseImageViewKenya = findViewById(R.id.chooseImageViewKenya)
        progressBar = findViewById(R.id.progressBar)

        mStorage = FirebaseStorage.getInstance()
        mStorageRef = FirebaseStorage.getInstance().getReference("kenyans_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("kenyans_uploads")

        chooseImageViewKenya.setOnClickListener { openFileChooser() }
        button_choose_image_kenya.setOnClickListener { openFileChooser() }
        upLoadBtnKenya.setOnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress) {
                Toast.makeText(this@KenyaUploadActivity,
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
            chooseImageViewKenya.setImageURI(mImageUri)
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
                    Toast.makeText(this@KenyaUploadActivity, "File Upload Successful", Toast.LENGTH_SHORT).show()
                    val upload = Kenya(
                        nameEditKenya.text.toString().trim { it <= ' ' },
                        mImageUri.toString(),
                        // descriptionEditText.text.toString().trim {it <= ' ' },
                        descriptionEditKenya.text.toString())
                    val uploadId = mDatabaseRef!!.push().key
                    mDatabaseRef!!.child(uploadId!!).setValue(upload)
                    progressBar.visibility = View.INVISIBLE
                    openImageActivity()
                }
                .addOnFailureListener { e->
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@KenyaUploadActivity, e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { tastSnpshot->
                    val mProgess = (100.0 * tastSnpshot.bytesTransferred/tastSnpshot.totalByteCount)
                    progressBar.progress = mProgess.toInt()
                }
        }else{
            Toast.makeText(this@KenyaUploadActivity,"You haven't selected any File", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openImageActivity() {
        startActivity(Intent(this@KenyaUploadActivity, KenyaFoodActivity::class.java))
    }
}