package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
class EntertainActivity : AppCompatActivity() {


    private lateinit var btnViewAllEntertain: Button
    private lateinit var btnAddInfoEntertain: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entertain)


        btnAddInfoEntertain = findViewById(R.id.btnAddInfoEntertain)
        btnViewAllEntertain = findViewById(R.id.btnViewAllEntertain)


        btnViewAllEntertain.setOnClickListener {
            startActivity(Intent(this, EntertainItemsActivity::class.java))
        }
        btnAddInfoEntertain.setOnClickListener {
            startActivity(Intent(this, EntertainUploadActivity::class.java))
        }
    }
}