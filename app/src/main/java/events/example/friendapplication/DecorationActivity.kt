package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class DecorationActivity : AppCompatActivity() {

    private lateinit var btnViewAllDeco: Button
    private lateinit var btnAddInfoDeco: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decoration)


        btnAddInfoDeco = findViewById(R.id.btnAddInfoDeco)
        btnViewAllDeco = findViewById(R.id.btnViewAllDeco)


        btnViewAllDeco.setOnClickListener {
            startActivity(Intent(this, DecoItemsActivity::class.java))
        }
        btnAddInfoDeco.setOnClickListener {
            startActivity(Intent(this, DecoUploadActivity::class.java))
        }


    }
}