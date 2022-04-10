package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TanzaniaEventActivity : AppCompatActivity() {


    private lateinit var btnViewAllEventTz: Button
    private lateinit var btnAddInfoEventTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_event)

        btnAddInfoEventTz = findViewById(R.id.btnAddInfoEventTz)
        btnViewAllEventTz = findViewById(R.id.btnViewAllEventTz)

        btnViewAllEventTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaEventItemsActivity::class.java))
        }
        btnAddInfoEventTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaEventUploadActivity::class.java))
        }

    }
}