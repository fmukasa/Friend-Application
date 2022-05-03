package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaEntertainActivity : AppCompatActivity() {

    private lateinit var btnViewAllEntertainKe: Button
    private lateinit var btnAddInfoEntertainKe: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_entertain)

        btnAddInfoEntertainKe = findViewById(R.id.btnAddInfoEntertainKe)
        btnViewAllEntertainKe = findViewById(R.id.btnViewAllEntertainKe)

        btnViewAllEntertainKe.setOnClickListener {
            startActivity(Intent(this, KenyaEntertainItemsActivity::class.java))
        }
        btnAddInfoEntertainKe.setOnClickListener {
            startActivity(Intent(this, KenyaEntertainUploadActivity::class.java))
        }


    }
}