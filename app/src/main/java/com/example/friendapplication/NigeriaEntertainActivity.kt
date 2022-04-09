package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NigeriaEntertainActivity : AppCompatActivity() {

    private lateinit var btnViewAllEntertainNa: Button
    private lateinit var btnAddInfoEntertainNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_entertain)


        btnAddInfoEntertainNa = findViewById(R.id.btnAddInfoEntertainNa)
        btnViewAllEntertainNa = findViewById(R.id.btnViewAllEntertainNa)


        btnViewAllEntertainNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEntertainItemsActivity::class.java))
        }
        btnAddInfoEntertainNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEntertainUploadActivity::class.java))
        }
    }
}