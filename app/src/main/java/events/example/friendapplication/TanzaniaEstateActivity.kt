package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class TanzaniaEstateActivity : AppCompatActivity() {

    private lateinit var btnViewAllEstateTz: Button
    private lateinit var btnAddInfoEstateTz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_estate)


        btnAddInfoEstateTz = findViewById(R.id.btnAddInfoEstateTz)
        btnViewAllEstateTz = findViewById(R.id.btnViewAllEstateTz)

        btnViewAllEstateTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaEstateItemsActivity::class.java))
        }
        btnAddInfoEstateTz.setOnClickListener {
            startActivity(Intent(this, TanzaniaEstateUploadActivity::class.java))
        }

    }
}