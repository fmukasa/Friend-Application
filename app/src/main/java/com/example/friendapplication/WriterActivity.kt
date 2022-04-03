package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WriterActivity : AppCompatActivity() {


    private lateinit var btnViewAllWriter: Button
    private lateinit var btnAddInfoWriter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writer)

        btnAddInfoWriter = findViewById(R.id.btnAddInfoWriter)
        btnViewAllWriter = findViewById(R.id.btnViewAllWriter)

        btnViewAllWriter.setOnClickListener {
            startActivity(Intent(this, WriterItemsActivity::class.java))
        }
        btnAddInfoWriter.setOnClickListener {
            startActivity(Intent(this, WriterUploadActivity::class.java))
        }
    }
}