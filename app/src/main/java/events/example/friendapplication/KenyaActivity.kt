package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class KenyaActivity : AppCompatActivity() {

    private lateinit var cateringBtnKe : ImageView
    private lateinit var decoBtnKe : ImageView
    private lateinit var equipBtnKe : ImageView
    private lateinit var promoBtnKe : ImageView
    private lateinit var entertainBtnKe: ImageView
    private lateinit var producerBtnKe : ImageView
    private lateinit var directorBtnKe : ImageView
    private lateinit var writerBtnKe : ImageView
    private lateinit var estateBtnKe : ImageView
    private lateinit var eventsBtnKe : ImageView
    private lateinit var serviceBtnKe : ImageView
    private lateinit var foundBtnKe : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya)

        cateringBtnKe = findViewById(R.id.cateringBtnKe)
        decoBtnKe = findViewById(R.id.decoBtnKe)
        equipBtnKe = findViewById(R.id.equipBtnKe)
        promoBtnKe = findViewById(R.id.promoBtnKe)
        entertainBtnKe = findViewById(R.id.entertainBtnKe)
        producerBtnKe = findViewById(R.id.producerBtnKe)
        directorBtnKe = findViewById(R.id.directorBtnKe)
        writerBtnKe = findViewById(R.id.writerBtnKe)
        estateBtnKe = findViewById(R.id.estateBtnKe)
        eventsBtnKe = findViewById(R.id.eventsBtnKe)
        serviceBtnKe = findViewById(R.id.serviceBtnKe)
        foundBtnKe = findViewById(R.id.foundBtnKe)


        cateringBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaFoodActivity::class.java))
        }
        decoBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaDecorationActivity::class.java))
        }
        equipBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaSoundActivity::class.java))
        }
        promoBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaPromoterActivity::class.java))
        }
        entertainBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaEntertainActivity::class.java))
        }
        producerBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaProducerActivity::class.java))
        }
        directorBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaDirectorActivity::class.java))
        }
        writerBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaWriterActivity::class.java))
        }
        estateBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaEstateActivity::class.java))
        }
        eventsBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaEventActivity::class.java))
        }
        serviceBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaServiceActivity::class.java))
        }
        foundBtnKe.setOnClickListener {
            startActivity(Intent(this, KenyaFoundActivity::class.java))
        }

    }
}