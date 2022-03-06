package com.example.friendapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.friendapplication.uitel.loadImage
import kotlinx.android.synthetic.main.activity_details.*

class SoundDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_details)


        val intss = intent
        val nameT = intss. getStringExtra("NAMET")
        val desT = intss. getStringExtra("DESCRIT")
        val imgT = intss. getStringExtra("IMGURI")

        nameDetailTextView.text = nameT
        descriptionDetailTextView.text = desT
        teacherDetailImageView.loadImage(imgT)
    }
}