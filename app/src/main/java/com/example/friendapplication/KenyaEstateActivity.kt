package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class KenyaEstateActivity : AppCompatActivity() {

    private lateinit var btnViewAllEstateKe: Button
    private lateinit var btnAddInfoEstateKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_estate)

        btnAddInfoEstateKe = findViewById(R.id.btnAddInfoEstateKe)
        btnViewAllEstateKe = findViewById(R.id.btnViewAllEstateKe)

        btnViewAllEstateKe.setOnClickListener {
            startActivity(Intent(this,KenyaEstateItemsActivity::class.java))
        }
        btnAddInfoEstateKe.setOnClickListener {
            startActivity(Intent(this,KenyaEstateUploadActivity::class.java))
        }


    }
}