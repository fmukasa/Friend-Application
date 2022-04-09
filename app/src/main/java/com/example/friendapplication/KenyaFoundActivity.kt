package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class KenyaFoundActivity : AppCompatActivity() {

    private lateinit var btnViewAllFoundKe: Button
    private lateinit var btnAddInfoFoundKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_found)

        btnAddInfoFoundKe = findViewById(R.id.btnAddInfoFoundKe)
        btnViewAllFoundKe = findViewById(R.id.btnViewAllFoundKe)

        btnViewAllFoundKe.setOnClickListener {
            startActivity(Intent(this, KenyaFoundItemsActivity::class.java))
        }
        btnAddInfoFoundKe.setOnClickListener {
            startActivity(Intent(this, KenyaFoundUploadActivity::class.java))
        }


    }
}