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
    private lateinit var textViewKenya: TextView
    private lateinit var imageViewKenya:ImageView
    private lateinit var textViewTanzania: TextView
    private lateinit var imageViewTanzania: ImageView
    private lateinit var checkBox: CheckBox
    private lateinit var checkBoxN: CheckBox
    private lateinit var checkBoxK:CheckBox
    private lateinit var checkBoxT: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        textViewUganda = findViewById(R.id.textViewUganda)
        imageViewUganda = findViewById(R.id.imageViewUganda)

        textViewNigeria = findViewById(R.id.textViewNigeria)
        imageViewNigeria = findViewById(R.id.imageViewNigeria)

        textViewKenya = findViewById(R.id.textViewKenya)
        imageViewKenya = findViewById(R.id.imageViewKenya)

        textViewTanzania = findViewById(R.id.textViewTanzania)
        imageViewTanzania = findViewById(R.id.imageViewTanzania)


        checkBox = findViewById(R.id.checkBox)
        checkBoxN= findViewById(R.id.checkBoxN)
        checkBoxK = findViewById(R.id.checkBoxK)
        checkBoxT = findViewById(R.id.checkBoxT)



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

        checkBoxK .setOnClickListener {
            startActivity(Intent(this@CountryActivity, KenyaActivity::class.java))
        }

        imageViewKenya .setOnClickListener {
            startActivity(Intent(this@CountryActivity, KenyaActivity::class.java))
        }

        imageViewKenya .setOnClickListener {
            startActivity(Intent(this@CountryActivity, KenyaActivity::class.java))
        }
        checkBoxT .setOnClickListener {
            startActivity(Intent(this@CountryActivity, TanzaniaActivity::class.java))
        }

        imageViewTanzania .setOnClickListener {
            startActivity(Intent(this@CountryActivity, TanzaniaActivity::class.java))
        }

        imageViewTanzania .setOnClickListener {
            startActivity(Intent(this@CountryActivity, TanzaniaActivity::class.java))
        }
    }
    }

