package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TanzaniaPromoterActivity : AppCompatActivity() {

    private lateinit var btnViewAllPromoTz: Button
    private lateinit var btnAddInfoPromoTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_promoter)

        btnAddInfoPromoTz = findViewById(R.id.btnAddInfoPromoTz)
        btnViewAllPromoTz = findViewById(R.id.btnViewAllPromoTz)

        btnViewAllPromoTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaPromoItemsActivity::class.java))
        }
        btnAddInfoPromoTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaPromoUploadActivity::class.java))
        }

    }
}