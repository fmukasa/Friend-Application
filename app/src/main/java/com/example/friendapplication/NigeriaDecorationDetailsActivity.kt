package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.friendapplication.uitel.loadImage
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_details.*

class NigeriaDecorationDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_decoration_details)
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
                    startActivity(Intent(applicationContext, NigeriaDecorationItemsActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.home ->  {
                    startActivity(Intent(applicationContext, NigeriaDecorationActivity::class.java))
                    overridePendingTransition(0, 0)
                    Toast.makeText(this, "Powered by Edimpressions", Toast.LENGTH_SHORT).show()
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