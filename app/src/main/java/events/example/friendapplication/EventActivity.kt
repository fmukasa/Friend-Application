package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class EventActivity : AppCompatActivity() {


    private lateinit var btnViewAllEvent: Button
    private lateinit var btnAddInfoEvent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        btnAddInfoEvent = findViewById(R.id.btnAddInfoEvent)
        btnViewAllEvent = findViewById(R.id.btnViewAllEvent)

        btnViewAllEvent.setOnClickListener {
            startActivity(Intent(this, EventItemsActivity::class.java))
        }
        btnAddInfoEvent.setOnClickListener {
            startActivity(Intent(this, EventUploadActivity::class.java))
        }
    }
}