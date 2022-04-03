package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FoundActivity : AppCompatActivity() {


    private lateinit var btnViewAllFound: Button
    private lateinit var btnAddInfoFound: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found)

        btnAddInfoFound = findViewById(R.id.btnAddInfoFound)
        btnViewAllFound = findViewById(R.id.btnViewAllFound)

        btnViewAllFound.setOnClickListener {
            startActivity(Intent(this, FoundItemsActivity::class.java))
        }
        btnAddInfoFound.setOnClickListener {
            startActivity(Intent(this, FoundUploadActivity::class.java))
        }

    }
}