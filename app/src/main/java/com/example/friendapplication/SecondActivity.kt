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
    private lateinit var promoBtn : ImageView
    private lateinit var entertainBtn: ImageView
    private lateinit var producerBtn : ImageView
    private lateinit var directorBtn : ImageView
    private lateinit var writerBtn : ImageView
    private lateinit var estateBtn : ImageView
    private lateinit var eventsBtn :ImageView
    private lateinit var serviceBtn : ImageView
    private lateinit var foundBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        cateringBtn = findViewById(R.id.cateringBtn)
        decoBtn = findViewById(R.id.decoBtn)
        equipBtn = findViewById(R.id.equipBtn)
        promoBtn = findViewById(R.id.promoBtn)
        entertainBtn = findViewById(R.id.entertainBtn)
        producerBtn = findViewById(R.id.producerBtn)
        directorBtn = findViewById(R.id.directorBtn)
        writerBtn = findViewById(R.id.writerBtn)
        estateBtn = findViewById(R.id.estateBtn)
        eventsBtn = findViewById(R.id.eventsBtn)
        serviceBtn = findViewById(R.id.serviceBtn)
        foundBtn = findViewById(R.id.foundBtn)


        cateringBtn.setOnClickListener {
            startActivity(Intent(this, FoodActivity::class.java))
        }
        decoBtn.setOnClickListener {
            startActivity(Intent(this, DecorationActivity::class.java))
        }
        equipBtn.setOnClickListener {
            startActivity(Intent(this, SoundActivity::class.java))
        }
        promoBtn.setOnClickListener {
            startActivity(Intent(this, PromoterActivity::class.java))
        }
        entertainBtn.setOnClickListener {
            startActivity(Intent(this, EntertainActivity::class.java))
        }
        producerBtn.setOnClickListener {
            startActivity(Intent(this, ProducerActivity::class.java))
        }
        directorBtn.setOnClickListener {
            startActivity(Intent(this, DirectorActivity::class.java))
        }
        writerBtn.setOnClickListener {
            startActivity(Intent(this, WriterActivity::class.java))
        }
        estateBtn.setOnClickListener {
            startActivity(Intent(this, EstateActivity::class.java))
        }
        eventsBtn.setOnClickListener {
            startActivity(Intent(this, EventActivity::class.java))
        }
        serviceBtn.setOnClickListener {
            startActivity(Intent(this, ServiceActivity::class.java))
        }
        foundBtn.setOnClickListener {
            startActivity(Intent(this, FoundActivity::class.java))
        }
    }
}