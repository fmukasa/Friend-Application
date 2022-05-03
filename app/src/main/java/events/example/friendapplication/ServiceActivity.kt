package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class ServiceActivity : AppCompatActivity() {


    private lateinit var btnViewAllService: Button
    private lateinit var btnAddInfoService: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)


        btnAddInfoService = findViewById(R.id.btnAddInfoService)
        btnViewAllService = findViewById(R.id.btnViewAllService)

        btnViewAllService.setOnClickListener {
            startActivity(Intent(this, ServiceItemsActivity::class.java))
        }
        btnAddInfoService.setOnClickListener {
            startActivity(Intent(this, ServiceUploadActivity::class.java))
        }
    }
}