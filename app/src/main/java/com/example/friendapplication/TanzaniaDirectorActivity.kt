package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TanzaniaDirectorActivity : AppCompatActivity() {


    private lateinit var btnViewAllDirectorTz: Button
    private lateinit var btnAddInfoDirectorTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_director)

        btnAddInfoDirectorTz = findViewById(R.id.btnAddInfoDirectorTz)
        btnViewAllDirectorTz = findViewById(R.id.btnViewAllDirectorTz)

        btnViewAllDirectorTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaDirectorItemsActivity::class.java))
        }
        btnAddInfoDirectorTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaDirectorUploadActivity::class.java))
        }


    }
}