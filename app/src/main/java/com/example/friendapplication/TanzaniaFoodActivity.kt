package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TanzaniaFoodActivity : AppCompatActivity() {


    private lateinit var btnViewAllTz: Button
    private lateinit var btnAddInfoTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_food)


        btnAddInfoTz = findViewById(R.id.btnAddInfoTz)
        btnViewAllTz = findViewById(R.id.btnViewAllTz)


        btnViewAllTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaItemsActivity::class.java))
        }
        btnAddInfoTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaUploadActivity::class.java))
        }


    }
}