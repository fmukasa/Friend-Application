package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class NigeriaWriterActivity : AppCompatActivity() {


    private lateinit var btnViewAllWriterNa: Button
    private lateinit var btnAddInfoWriterNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_writer)


        btnAddInfoWriterNa = findViewById(R.id.btnAddInfoWriterNa)
        btnViewAllWriterNa = findViewById(R.id.btnViewAllWriterNa)

        btnViewAllWriterNa.setOnClickListener {
            startActivity(Intent(this, NigeriaWriterItemsActivity::class.java))
        }
        btnAddInfoWriterNa.setOnClickListener {
            startActivity(Intent(this, NigeriaWriterUploadActivity::class.java))
        }

    }
}