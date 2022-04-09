package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NigeriaFoundActivity : AppCompatActivity() {

    private lateinit var btnViewAllFoundNa: Button
    private lateinit var btnAddInfoFoundNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_found)

        btnAddInfoFoundNa = findViewById(R.id.btnAddInfoFoundNa)
        btnViewAllFoundNa = findViewById(R.id.btnViewAllFoundNa)

        btnViewAllFoundNa.setOnClickListener {
            startActivity(Intent(this, NigeriaFoundItemsActivity::class.java))
        }
        btnAddInfoFoundNa.setOnClickListener {
            startActivity(Intent(this, NigeriaFoundUploadActivity::class.java))
        }

    }
}