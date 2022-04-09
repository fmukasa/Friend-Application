package com.example.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class NigeriaActivity : AppCompatActivity() {

    private lateinit var cateringBtnNa : ImageView
    private lateinit var decoBtnNa : ImageView
    private lateinit var equipBtnNa : ImageView
    private lateinit var promoBtnNa : ImageView
    private lateinit var entertainBtnNa: ImageView
    private lateinit var producerBtnNa : ImageView
    private lateinit var directorBtnNa : ImageView
    private lateinit var writerBtnNa : ImageView
    private lateinit var estateBtnNa : ImageView
    private lateinit var eventsBtnNa : ImageView
    private lateinit var serviceBtnNa : ImageView
    private lateinit var foundBtnNa : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria)


        cateringBtnNa = findViewById(R.id.cateringBtnNa)
        decoBtnNa = findViewById(R.id.decoBtnNa)
        equipBtnNa = findViewById(R.id.equipBtnNa)
        promoBtnNa = findViewById(R.id.promoBtnNa)
        entertainBtnNa = findViewById(R.id.entertainBtnNa)
        producerBtnNa = findViewById(R.id.producerBtnNa)
        directorBtnNa = findViewById(R.id.directorBtnNa)
        writerBtnNa = findViewById(R.id.writerBtnNa)
        estateBtnNa = findViewById(R.id.estateBtnNa)
        eventsBtnNa = findViewById(R.id.eventsBtnNa)
        serviceBtnNa = findViewById(R.id.serviceBtnNa)
        foundBtnNa = findViewById(R.id.foundBtnNa)


        cateringBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaFoodActivity::class.java))
        }
        decoBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaDecorationActivity::class.java))
        }
        equipBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaSoundActivity::class.java))
        }
        promoBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaPromoterActivity::class.java))
        }
        entertainBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEntertainActivity::class.java))
        }
        producerBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaProducerActivity::class.java))
        }
        directorBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaDirectorActivity::class.java))
        }
        writerBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaWriterActivity::class.java))
        }
        estateBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEstateActivity::class.java))
        }
        eventsBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEventActivity::class.java))
        }
        serviceBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaServiceActivity::class.java))
        }
        foundBtnNa.setOnClickListener {
            startActivity(Intent(this, NigeriaFoundActivity::class.java))
        }

    }
}