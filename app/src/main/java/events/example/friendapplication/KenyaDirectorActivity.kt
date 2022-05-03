package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaDirectorActivity : AppCompatActivity() {


    private lateinit var btnViewAllDirectorKe: Button
    private lateinit var btnAddInfoDirectorKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_director)

        btnAddInfoDirectorKe = findViewById(R.id.btnAddInfoDirectorKe)
        btnViewAllDirectorKe = findViewById(R.id.btnViewAllDirectorKe)

        btnViewAllDirectorKe.setOnClickListener {
            startActivity(Intent(this, KenyaDirectorItemsActivity::class.java))
        }
        btnAddInfoDirectorKe.setOnClickListener {
            startActivity(Intent(this, KenyaDirectorUploadActivity::class.java))
        }


    }
}