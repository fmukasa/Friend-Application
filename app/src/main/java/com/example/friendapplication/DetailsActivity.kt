package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.example.friendapplication.uitel.loadImage
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val intss = intent
        val nameT = intss. getStringExtra("NAMET")
        val desT = intss. getStringExtra("DESCRIT")
        val imgT = intss. getStringExtra("IMGURI")

        nameDetailTextView.text = nameT
        descriptionDetailTextView.text = desT
        teacherDetailImageView.loadImage(imgT)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.selectedItemId = R.id.home


        bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.category -> {
                    startActivity(Intent(applicationContext, ItemsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.home ->  {
                    startActivity(Intent(applicationContext, FoodActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }

                R.id.listview -> {
                    startActivity(Intent(applicationContext, SecondActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }


    }
}