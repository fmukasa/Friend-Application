package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class DirectorActivity : AppCompatActivity() {


    private lateinit var btnViewAllDirector: Button
    private lateinit var btnAddInfoDirector: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_director)


        btnAddInfoDirector = findViewById(R.id.btnAddInfoDirector)
        btnViewAllDirector = findViewById(R.id.btnViewAllDirector)

        btnViewAllDirector.setOnClickListener {
            startActivity(Intent(this, DirectorItemsActivity::class.java))
        }
        btnAddInfoDirector.setOnClickListener {
            startActivity(Intent(this, DirectorUploadActivity::class.java))
        }
    }
}