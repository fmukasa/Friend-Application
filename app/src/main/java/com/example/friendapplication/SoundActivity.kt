package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SoundActivity : AppCompatActivity() {

    private lateinit var btnAddInfoEquip:Button
    private lateinit var btnViewAllEquip:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)


        btnAddInfoEquip = findViewById(R.id.btnAddInfoEquip)
        btnViewAllEquip = findViewById(R.id.btnViewAllEquip)


        btnViewAllEquip.setOnClickListener {
            startActivity(Intent(this, SoundItemsActivity::class.java))
        }
        btnAddInfoEquip.setOnClickListener {
            startActivity(Intent(this, SoundUploadActivity::class.java))
        }



    }
}