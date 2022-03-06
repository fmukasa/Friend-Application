package com.example.friendapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.friendapplication.uitel.loadImage
import kotlinx.android.synthetic.main.activity_details.*


class DecoDetailsActivity : AppCompatActivity() {

    /**private lateinit var nameDecoTextView: TextView
    private lateinit var descriptionDecoTextView: TextView
    private lateinit var DecoImageView : ImageView*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deco_details)

       /** nameDecoTextView = findViewById(R.id.nameDecoTextView)
        descriptionDecoTextView= findViewById(R.id.descriptionDecoTextView)
        DecoImageView = findViewById(R.id.DecoImageView)*/


       val intss = intent
        val nameT = intss. getStringExtra("NAMET")
        val desT = intss. getStringExtra("DESCRIT")
        val imgT = intss. getStringExtra("IMGURI")

        nameDetailTextView.text = nameT
        descriptionDetailTextView.text = desT
        teacherDetailImageView.loadImage(imgT)
    }
}