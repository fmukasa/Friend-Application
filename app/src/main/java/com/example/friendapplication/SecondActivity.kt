package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class SecondActivity : AppCompatActivity() {

    private lateinit var cateringBtn :ImageView
    private lateinit var decoBtn :ImageView
    private lateinit var equipBtn :ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        cateringBtn = findViewById(R.id.cateringBtn)
        decoBtn = findViewById(R.id.decoBtn)
        equipBtn = findViewById(R.id.equipBtn)


        cateringBtn.setOnClickListener {
            startActivity(Intent(this, FoodActivity::class.java))
        }
        decoBtn.setOnClickListener {
            startActivity(Intent(this, DecorationActivity::class.java))
        }
        equipBtn.setOnClickListener {
            startActivity(Intent(this, EquipmentActivity::class.java))
        }
    }
}