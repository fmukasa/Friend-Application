package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class KenyaSoundActivity : AppCompatActivity() {

    private lateinit var btnAddInfoEquipKe: Button
    private lateinit var btnViewAllEquipKe: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kenya_sound)

        btnAddInfoEquipKe = findViewById(R.id.btnAddInfoEquipKe)
        btnViewAllEquipKe = findViewById(R.id.btnViewAllEquipKe)


        btnViewAllEquipKe.setOnClickListener {
            startActivity(Intent(this, KenyaSoundItemsActivity::class.java))
        }
        btnAddInfoEquipKe.setOnClickListener {
            startActivity(Intent(this, KenyaSoundUploadActivity::class.java))
        }


    }
}