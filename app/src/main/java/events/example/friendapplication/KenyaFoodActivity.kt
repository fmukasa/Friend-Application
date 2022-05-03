package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaFoodActivity : AppCompatActivity() {

    private lateinit var btnViewAllKe: Button
    private lateinit var btnAddInfoKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_food)

        btnAddInfoKe = findViewById(R.id.btnAddInfoKe)
        btnViewAllKe = findViewById(R.id.btnViewAllKe)


        btnViewAllKe.setOnClickListener {
            startActivity(Intent(this, KenyaItemsActivity::class.java))
        }
        btnAddInfoKe.setOnClickListener {
            startActivity(Intent(this, KenyaUploadActivity::class.java))
        }

    }
}