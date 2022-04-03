package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProducerActivity : AppCompatActivity() {

    private lateinit var btnViewAllProducer: Button
    private lateinit var btnAddInfoProducer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producer)

        btnAddInfoProducer = findViewById(R.id.btnAddInfoProducer)
        btnViewAllProducer = findViewById(R.id.btnViewAllProducer)

        btnViewAllProducer.setOnClickListener {
            startActivity(Intent(this, ProducerItemsActivity::class.java))
        }
        btnAddInfoProducer.setOnClickListener {
            startActivity(Intent(this, ProducerUploadActivity::class.java))
        }
    }
}