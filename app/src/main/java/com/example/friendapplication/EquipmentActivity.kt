package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class EquipmentActivity : AppCompatActivity() {

           private lateinit var soundBtn: ImageView
           private lateinit var stageBtn: ImageView
           private lateinit var generatorBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipment)

        soundBtn = findViewById(R.id.soundBtn)
        stageBtn = findViewById(R.id.stageBtn)
        generatorBtn= findViewById(R.id.generatorBtn)

       soundBtn.setOnClickListener {
            startActivity(Intent(this, SoundActivity::class.java))
        }
        stageBtn.setOnClickListener {
            startActivity(Intent(this, SoundActivity::class.java))
        }
        generatorBtn.setOnClickListener {
            startActivity(Intent(this, SoundActivity::class.java))
        }
    }
}