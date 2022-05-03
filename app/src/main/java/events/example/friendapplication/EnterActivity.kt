package events.app.friendapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class EnterActivity : AppCompatActivity() {

    private lateinit var enterBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)


        enterBtn = findViewById(R.id.enterBtn)

        enterBtn .setOnClickListener {
            startActivity(Intent(this@EnterActivity, LoginActivity::class.java))
        }

    }
}