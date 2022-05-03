package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaEventActivity : AppCompatActivity() {

    private lateinit var btnViewAllEventKe: Button
    private lateinit var btnAddInfoEventKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_event)

        btnAddInfoEventKe = findViewById(R.id.btnAddInfoEventKe)
        btnViewAllEventKe = findViewById(R.id.btnViewAllEventKe)

        btnViewAllEventKe.setOnClickListener {
            startActivity(Intent(this, KenyaEventItemsActivity::class.java))
        }
        btnAddInfoEventKe.setOnClickListener {
            startActivity(Intent(this, KenyaEventUploadActivity::class.java))
        }
    }
}