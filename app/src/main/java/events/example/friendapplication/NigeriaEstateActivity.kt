package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class NigeriaEstateActivity : AppCompatActivity() {

    private lateinit var btnViewAllEstateNa: Button
    private lateinit var btnAddInfoEstateNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_estate)

        btnAddInfoEstateNa = findViewById(R.id.btnAddInfoEstateNa)
        btnViewAllEstateNa = findViewById(R.id.btnViewAllEstateNa)

        btnViewAllEstateNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEstateItemsActivity::class.java))
        }
        btnAddInfoEstateNa.setOnClickListener {
            startActivity(Intent(this, NigeriaEstateUploadActivity::class.java))
        }

    }
}