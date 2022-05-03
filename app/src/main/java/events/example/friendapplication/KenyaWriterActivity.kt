package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaWriterActivity : AppCompatActivity() {

    private lateinit var btnViewAllWriterKe: Button
    private lateinit var btnAddInfoWriterKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_writer)

        btnAddInfoWriterKe = findViewById(R.id.btnAddInfoWriterKe)
        btnViewAllWriterKe = findViewById(R.id.btnViewAllWriterKe)

        btnViewAllWriterKe.setOnClickListener {
            startActivity(Intent(this, KenyaWriterItemsActivity::class.java))
        }
        btnAddInfoWriterKe.setOnClickListener {
            startActivity(Intent(this, KenyaWriterUploadActivity::class.java))
        }

    }
}