package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaProducerActivity : AppCompatActivity() {

    private lateinit var btnViewAllProducerKe: Button
    private lateinit var btnAddInfoProducerKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_producer)

        btnAddInfoProducerKe = findViewById(R.id.btnAddInfoProducerKe)
        btnViewAllProducerKe = findViewById(R.id.btnViewAllProducerKe)

        btnViewAllProducerKe.setOnClickListener {
            startActivity(Intent(this, KenyaProducerItemsActivity::class.java))
        }
        btnAddInfoProducerKe.setOnClickListener {
            startActivity(Intent(this, KenyaProducerUploadActivity::class.java))
        }

    }
}