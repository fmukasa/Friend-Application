package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TanzaniaFoundActivity : AppCompatActivity() {

    private lateinit var btnViewAllFoundTz: Button
    private lateinit var btnAddInfoFoundTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_found)

        btnAddInfoFoundTz = findViewById(R.id.btnAddInfoFoundTz)
        btnViewAllFoundTz = findViewById(R.id.btnViewAllFoundTz)

        btnViewAllFoundTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaFoundItemsActivity::class.java))
        }
        btnAddInfoFoundTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaFoundUploadActivity::class.java))
        }

    }
}