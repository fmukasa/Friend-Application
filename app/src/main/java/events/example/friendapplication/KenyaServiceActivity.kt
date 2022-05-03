package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaServiceActivity : AppCompatActivity() {


    private lateinit var btnViewAllServiceKe: Button
    private lateinit var btnAddInfoServiceKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_service)

        btnAddInfoServiceKe = findViewById(R.id.btnAddInfoServiceKe)
        btnViewAllServiceKe = findViewById(R.id.btnViewAllServiceKe)

        btnViewAllServiceKe.setOnClickListener {
            startActivity(Intent(this, KenyaServiceItemsActivity::class.java))
        }
        btnAddInfoServiceKe.setOnClickListener {
            startActivity(Intent(this, KenyaServiceUploadActivity::class.java))
        }


    }
}