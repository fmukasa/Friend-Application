package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NigeriaServiceActivity : AppCompatActivity() {


    private lateinit var btnViewAllServiceNa: Button
    private lateinit var btnAddInfoServiceNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_service)

        btnAddInfoServiceNa = findViewById(R.id.btnAddInfoServiceNa)
        btnViewAllServiceNa = findViewById(R.id.btnViewAllServiceNa)

        btnViewAllServiceNa.setOnClickListener {
            startActivity(Intent(this, NigeriaServiceItemsActivity::class.java))
        }
        btnAddInfoServiceNa.setOnClickListener {
            startActivity(Intent(this, NigeriaServiceUploadActivity::class.java))
        }

    }
}