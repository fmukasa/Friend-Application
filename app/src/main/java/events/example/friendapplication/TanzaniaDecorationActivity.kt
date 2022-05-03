package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class TanzaniaDecorationActivity : AppCompatActivity() {

    private lateinit var btnViewAllTzDeco: Button
    private lateinit var btnAddInfoTzDeco: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanzania_decoration)


        btnAddInfoTzDeco = findViewById(R.id.btnAddInfoTzDeco)
        btnViewAllTzDeco = findViewById(R.id.btnViewAllTzDeco)


        btnViewAllTzDeco.setOnClickListener {
            startActivity(Intent(this, TanzaniaDecorationItemsActivity::class.java))
        }
        btnAddInfoTzDeco.setOnClickListener {
            startActivity(Intent(this, TanzaniaDecorationUploadActivity::class.java))
        }

    }
}