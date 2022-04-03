package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class CountryActivity : AppCompatActivity() {

    private lateinit var textViewUganda:TextView
    private lateinit var imageViewUganda: ImageView
    private lateinit var textViewNigeria:TextView
    private lateinit var imageViewNigeria: ImageView
    private lateinit var checkBox: CheckBox
    private lateinit var checkBoxN: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        textViewUganda = findViewById(R.id.textViewUganda)
        imageViewUganda = findViewById(R.id.imageViewUganda)

        textViewNigeria = findViewById(R.id.textViewNigeria)
        imageViewNigeria = findViewById(R.id.imageViewNigeria)
        checkBox = findViewById(R.id.checkBox)
        checkBoxN= findViewById(R.id.checkBoxN)



        checkBox .setOnClickListener {
            startActivity(Intent(this@CountryActivity, SecondActivity::class.java))
        }

        textViewUganda .setOnClickListener {
            startActivity(Intent(this@CountryActivity, SecondActivity::class.java))
    }

        imageViewUganda .setOnClickListener {
            startActivity(Intent(this@CountryActivity, SecondActivity::class.java))
        }

       checkBoxN .setOnClickListener {
            startActivity(Intent(this@CountryActivity, NigeriaActivity::class.java))
        }

        imageViewNigeria .setOnClickListener {
            startActivity(Intent(this@CountryActivity, NigeriaActivity::class.java))
        }

        imageViewNigeria .setOnClickListener {
            startActivity(Intent(this@CountryActivity, NigeriaActivity::class.java))
        }
    }
    }

