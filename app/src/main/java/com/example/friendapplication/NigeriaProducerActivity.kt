package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NigeriaProducerActivity : AppCompatActivity() {

    private lateinit var btnViewAllProducerNa: Button
    private lateinit var btnAddInfoProducerNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_producer)

        btnAddInfoProducerNa = findViewById(R.id.btnAddInfoProducerNa)
        btnViewAllProducerNa = findViewById(R.id.btnViewAllProducerNa)

        btnViewAllProducerNa.setOnClickListener {
            startActivity(Intent(this, NigeriaProducerItemsActivity::class.java))
        }
        btnAddInfoProducerNa.setOnClickListener {
            startActivity(Intent(this, NigeriaProducerUploadActivity::class.java))
        }
    }
}