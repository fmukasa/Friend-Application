package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class TanzaniaEntertainActivity : AppCompatActivity() {

    private lateinit var btnViewAllEntertainTz: Button
    private lateinit var btnAddInfoEntertainTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_entertain)

        btnAddInfoEntertainTz = findViewById(R.id.btnAddInfoEntertainTz)
        btnViewAllEntertainTz = findViewById(R.id.btnViewAllEntertainTz)

        btnViewAllEntertainTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaEntertainItemsActivity::class.java))
        }
        btnAddInfoEntertainTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaEntertainUploadActivity::class.java))
        }


    }
}