package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TanzaniaServiceActivity : AppCompatActivity() {

    private lateinit var btnViewAllServiceTz: Button
    private lateinit var btnAddInfoServiceTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_service)

        btnAddInfoServiceTz = findViewById(R.id.btnAddInfoServiceTz)
        btnViewAllServiceTz = findViewById(R.id.btnViewAllServiceTz)

        btnViewAllServiceTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaServiceItemsActivity::class.java))
        }
        btnAddInfoServiceTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaServiceUploadActivity::class.java))
        }


    }
}