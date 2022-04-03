package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NigeriaFoodActivity : AppCompatActivity() {

    private lateinit var btnViewAllNa: Button
    private lateinit var btnAddInfoNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_food)

        btnAddInfoNa = findViewById(R.id.btnAddInfoNa)
        btnViewAllNa = findViewById(R.id.btnViewAllNa)


        btnViewAllNa.setOnClickListener {
            startActivity(Intent(this, NigeriaItemsActivity::class.java))
        }
        btnAddInfoNa.setOnClickListener {
            startActivity(Intent(this, NigeriaUploadActivity::class.java))
        }

    }
}