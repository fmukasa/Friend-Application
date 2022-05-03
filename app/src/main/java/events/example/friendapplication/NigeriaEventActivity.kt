package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class NigeriaEventActivity : AppCompatActivity() {


    private lateinit var btnViewAllEventNa: Button
    private lateinit var btnAddInfoEventNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_event)

        btnAddInfoEventNa = findViewById(R.id.btnAddInfoEventNa)
        btnViewAllEventNa = findViewById(R.id.btnViewAllEventNa)

        btnViewAllEventNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEventItemsActivity::class.java))
        }
        btnAddInfoEventNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEventUploadActivity::class.java))
        }
    }
}