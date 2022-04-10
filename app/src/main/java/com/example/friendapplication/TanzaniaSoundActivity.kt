package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TanzaniaSoundActivity : AppCompatActivity() {

    private lateinit var btnAddInfoEquipTz: Button
    private lateinit var btnViewAllEquipTz: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_sound)

        btnAddInfoEquipTz = findViewById(R.id.btnAddInfoEquipTz)
        btnViewAllEquipTz = findViewById(R.id.btnViewAllEquipTz)


        btnViewAllEquipTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaSoundItemsActivity::class.java))
        }
        btnAddInfoEquipTz.setOnClickListener {
            startActivity(Intent(this,TanzaniaSoundUploadActivity::class.java))
        }

    }
}