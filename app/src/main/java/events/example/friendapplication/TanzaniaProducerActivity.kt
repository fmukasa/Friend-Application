package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class TanzaniaProducerActivity : AppCompatActivity() {

    private lateinit var btnViewAllProducerTz: Button
    private lateinit var btnAddInfoProducerTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_producer)

        btnAddInfoProducerTz = findViewById(R.id.btnAddInfoProducerTz)
        btnViewAllProducerTz = findViewById(R.id.btnViewAllProducerTz)

        btnViewAllProducerTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaProducerItemsActivity::class.java))
        }
        btnAddInfoProducerTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaProducerUploadActivity::class.java))
        }


    }
}