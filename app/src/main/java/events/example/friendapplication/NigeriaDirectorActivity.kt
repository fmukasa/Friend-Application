package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class NigeriaDirectorActivity : AppCompatActivity() {


    private lateinit var btnViewAllDirectorNa: Button
    private lateinit var btnAddInfoDirectorNa: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nigeria_director)

        btnAddInfoDirectorNa = findViewById(R.id.btnAddInfoDirectorNa)
        btnViewAllDirectorNa = findViewById(R.id.btnViewAllDirectorNa)

        btnViewAllDirectorNa.setOnClickListener {
            startActivity(Intent(this, NigeriaDirectorItemsActivity::class.java))
        }
        btnAddInfoDirectorNa.setOnClickListener {
            startActivity(Intent(this, NigeriaDirectorUploadActivity::class.java))
        }
    }
}