package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NigeriaSoundActivity : AppCompatActivity() {

    private lateinit var btnAddInfoEquipNa: Button
    private lateinit var btnViewAllEquipNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_sound)

        btnAddInfoEquipNa = findViewById(R.id.btnAddInfoEquipNa)
        btnViewAllEquipNa = findViewById(R.id.btnViewAllEquipNa)


        btnViewAllEquipNa.setOnClickListener {
            startActivity(Intent(this, NigeriaSoundItemsActivity::class.java))
        }
        btnAddInfoEquipNa.setOnClickListener {
            startActivity(Intent(this, NigeriaSoundUploadActivity::class.java))
        }


    }
}