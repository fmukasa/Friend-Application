package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaPromoterActivity : AppCompatActivity() {

    private lateinit var btnViewAllPromoKe: Button
    private lateinit var btnAddInfoPromoKe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_promoter)

        btnAddInfoPromoKe = findViewById(R.id.btnAddInfoPromoKe)
        btnViewAllPromoKe = findViewById(R.id.btnViewAllPromoKe)

        btnViewAllPromoKe.setOnClickListener {
            startActivity(Intent(this, KenyaPromoItemsActivity::class.java))
        }
        btnAddInfoPromoKe.setOnClickListener {
            startActivity(Intent(this, KenyaPromoUploadActivity::class.java))
        }


    }
}