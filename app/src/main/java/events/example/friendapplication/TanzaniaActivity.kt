package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class TanzaniaActivity : AppCompatActivity() {


    private lateinit var cateringBtnTz : ImageView
    private lateinit var decoBtnTz : ImageView
    private lateinit var equipBtnTz : ImageView
    private lateinit var promoBtnTz : ImageView
    private lateinit var entertainBtnTz: ImageView
    private lateinit var producerBtnTz : ImageView
    private lateinit var directorBtnTz : ImageView
    private lateinit var writerBtnTz : ImageView
    private lateinit var estateBtnTz : ImageView
    private lateinit var eventsBtnTz : ImageView
    private lateinit var serviceBtnTz : ImageView
    private lateinit var foundBtnTz : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania)

        cateringBtnTz = findViewById(R.id.cateringBtnTz)
        decoBtnTz = findViewById(R.id.decoBtnTz)
        equipBtnTz = findViewById(R.id.equipBtnTz)
        promoBtnTz = findViewById(R.id.promoBtnTz)
        entertainBtnTz = findViewById(R.id.entertainBtnTz)
        producerBtnTz = findViewById(R.id.producerBtnTz)
        directorBtnTz = findViewById(R.id.directorBtnTz)
        writerBtnTz = findViewById(R.id.writerBtnTz)
        estateBtnTz = findViewById(R.id.estateBtnTz)
        eventsBtnTz = findViewById(R.id.eventsBtnTz)
        serviceBtnTz = findViewById(R.id.serviceBtnTz)
        foundBtnTz = findViewById(R.id.foundBtnTz)


        cateringBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaFoodActivity::class.java))
        }
        decoBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaDecorationActivity::class.java))
        }
        equipBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaSoundActivity::class.java))
        }
        promoBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaPromoterActivity::class.java))
        }
        entertainBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaEntertainActivity::class.java))
        }
        producerBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaProducerActivity::class.java))
        }
        directorBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaDirectorActivity::class.java))
        }
        writerBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaWriterActivity::class.java))
        }
        estateBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaEstateActivity::class.java))
        }
        eventsBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaEventActivity::class.java))
        }
        serviceBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaServiceActivity::class.java))
        }
        foundBtnTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaFoundActivity::class.java))
        }

    }
}