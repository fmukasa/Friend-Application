package com.example.friendapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import com.example.friendapplication.model.KenyaPromoter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask

class TanzaniaPromoUploadActivity : AppCompatActivity() {

    private  var  mImageUri : Uri? = null
    private var mStorage: FirebaseStorage? = null
    private var mStorageRef : StorageReference? = null
    private var mDatabaseRef : DatabaseReference? = null
    private var mUploadTask : StorageTask<*>? = null
    private val PICK_IMAGE_REQUEST = 1

    private lateinit var button_choose_image_promoTz: Button
    private lateinit var upLoadBtnPromoTz : Button
    private lateinit var nameEditPromoTz: EditText
    private lateinit var descriptionEditPromoTz: EditText
    private lateinit var chooseImageViewPromoTz: ImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_promo_upload)

        button_choose_image_promoTz = findViewById(R.id.button_choose_image_promoTz)
        upLoadBtnPromoTz = findViewById(R.id.upLoadBtnPromoTz)
        nameEditPromoTz = findViewById(R.id.nameEditPromoTz)
        descriptionEditPromoTz = findViewById(R.id.descriptionEditPromoTz)
        chooseImageViewPromoTz = findViewById(R.id.chooseImageViewPromoTz)
        progressBar = findViewById(R.id.progressBar)

        mStorage = FirebaseStorage.getInstance()
        mStorageRef = FirebaseStorage.getInstance().getReference("promoters_tz_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("promoters_tz_uploads")

        chooseImageViewPromoTz.setOnClickListener { openFileChooser() }
        button_choose_image_promoTz.setOnClickListener { openFileChooser() }
        upLoadBtnPromoTz.setOnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress) {
                Toast.makeText(this@TanzaniaPromoUploadActivity,
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
            chooseImageViewPromoTz.setImageURI(mImageUri)
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
                    Toast.makeText(this@TanzaniaPromoUploadActivity, "File Upload Successful", Toast.LENGTH_SHORT).show()
                    val upload = KenyaPromoter(
                        nameEditPromoTz.text.toString().trim { it <= ' ' },
                        mImageUri.toString(),
                        // descriptionEditText.text.toString().trim {it <= ' ' },
                        descriptionEditPromoTz.text.toString()


                    )
                    val uploadId = mDatabaseRef!!.push().key
                    mDatabaseRef!!.child(uploadId!!).setValue(upload)
                    progressBar.visibility = View.INVISIBLE
                    openImageActivity()
                }
                .addOnFailureListener { e->
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@TanzaniaPromoUploadActivity, e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { tastSnpshot->
                    val mProgess = (100.0 * tastSnpshot.bytesTransferred/tastSnpshot.totalByteCount)
                    progressBar.progress = mProgess.toInt()
                }
        }else{
            Toast.makeText(this@TanzaniaPromoUploadActivity,"You haven't selected any File", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openImageActivity() {
        startActivity(Intent(this@TanzaniaPromoUploadActivity, TanzaniaPromoterActivity::class.java))
    }
}
