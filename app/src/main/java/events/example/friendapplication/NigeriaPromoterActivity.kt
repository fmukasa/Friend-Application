package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class NigeriaPromoterActivity : AppCompatActivity() {

    private lateinit var btnViewAllPromoNa: Button
    private lateinit var btnAddInfoPromoNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_promoter)

        btnAddInfoPromoNa = findViewById(R.id.btnAddInfoPromoNa)
        btnViewAllPromoNa = findViewById(R.id.btnViewAllPromoNa)

        btnViewAllPromoNa.setOnClickListener {
            startActivity(Intent(this, NigeriaPromoItemsActivity::class.java))
        }
        btnAddInfoPromoNa.setOnClickListener {
            startActivity(Intent(this, NigeriaPromoUploadActivity::class.java))
        }
    }
}