package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TanzaniaWriterActivity : AppCompatActivity() {

    private lateinit var btnViewAllWriterTz: Button
    private lateinit var btnAddInfoWriterTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_writer)


        btnAddInfoWriterTz = findViewById(R.id.btnAddInfoWriterTz)
        btnViewAllWriterTz = findViewById(R.id.btnViewAllWriterTz)

        btnViewAllWriterTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaWriterItemsActivity::class.java))
        }
        btnAddInfoWriterTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaWriterUploadActivity::class.java))
        }

    }
}