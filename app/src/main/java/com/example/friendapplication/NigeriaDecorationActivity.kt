package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NigeriaDecorationActivity : AppCompatActivity() {

    private lateinit var btnViewAllNaDeco: Button
    private lateinit var btnAddInfoNaDeco: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_decoration)

        btnAddInfoNaDeco = findViewById(R.id.btnAddInfoNaDeco)
        btnViewAllNaDeco = findViewById(R.id.btnViewAllNaDeco)


        btnViewAllNaDeco.setOnClickListener {
            startActivity(Intent(this, NigeriaDecorationItemsActivity::class.java))
        }
        btnAddInfoNaDeco.setOnClickListener {
            startActivity(Intent(this, NigeriaDecorationUploadActivity::class.java))
        }

    }
}