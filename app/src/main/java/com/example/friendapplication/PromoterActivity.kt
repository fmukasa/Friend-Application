package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PromoterActivity : AppCompatActivity() {

    private lateinit var btnViewAllPromo: Button
    private lateinit var btnAddInfoPromo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promoter)


        btnAddInfoPromo = findViewById(R.id.btnAddInfoPromo)
        btnViewAllPromo = findViewById(R.id.btnViewAllPromo)


        btnViewAllPromo.setOnClickListener {
            startActivity(Intent(this, PromoItemsActivity::class.java))
        }
        btnAddInfoPromo.setOnClickListener {
            startActivity(Intent(this, PromoUploadActivity::class.java))
        }
    }
}