package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaDecorationActivity : AppCompatActivity() {

    private lateinit var btnViewAllKeDeco: Button
    private lateinit var btnAddInfoKeDeco: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_decoration)


        btnAddInfoKeDeco = findViewById(R.id.btnAddInfoKeDeco)
        btnViewAllKeDeco = findViewById(R.id.btnViewAllKeDeco)


        btnViewAllKeDeco.setOnClickListener {
            startActivity(Intent(this, KenyaDecorationItemsActivity::class.java))
        }
        btnAddInfoKeDeco.setOnClickListener {
            startActivity(Intent(this, KenyaDecorationUploadActivity::class.java))
        }
    }
}