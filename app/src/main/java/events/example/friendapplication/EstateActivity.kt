package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EstateActivity : AppCompatActivity() {

    private lateinit var btnViewAllEstate: Button
    private lateinit var btnAddInfoEstate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estate)

        btnAddInfoEstate = findViewById(R.id.btnAddInfoEstate)
        btnViewAllEstate = findViewById(R.id.btnViewAllEstate)

        btnViewAllEstate.setOnClickListener {
            startActivity(Intent(this, EstateItemsActivity::class.java))
        }
        btnAddInfoEstate.setOnClickListener {
            startActivity(Intent(this, EstateUploadActivity::class.java))
        }
    }
}