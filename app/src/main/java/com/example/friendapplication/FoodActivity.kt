package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FoodActivity : AppCompatActivity() {
         private lateinit var btnViewAll:Button
         private lateinit var btnAddInfo:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

          btnAddInfo = findViewById(R.id.btnAddInfo)
          btnViewAll = findViewById(R.id.btnViewAll)


        btnViewAll.setOnClickListener {
            startActivity(Intent(this, ItemsActivity::class.java))
        }
        btnAddInfo.setOnClickListener {
            startActivity(Intent(this, UploadActivity::class.java))
        }
    }
}