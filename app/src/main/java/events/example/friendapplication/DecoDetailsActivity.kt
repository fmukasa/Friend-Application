package events.app.friendapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import events.app.friendapplication.uitel.loadImage
import kotlinx.android.synthetic.main.activity_details.*


class DecoDetailsActivity : AppCompatActivity() {

    /**private lateinit var nameDecoTextView: TextView
    private lateinit var descriptionDecoTextView: TextView
    private lateinit var DecoImageView : ImageView*/
   /** private lateinit var mbottomNavigationView: BottomNavigationView
    private lateinit var tvContenName: TextView*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(events.app.friendapplication.R.layout.activity_deco_details)

       /** nameDecoTextView = findViewById(R.id.nameDecoTextView)
        descriptionDecoTextView= findViewById(R.id.descriptionDecoTextView)
        DecoImageView = findViewById(R.id.DecoImageView)*/


       val intss = intent
        val nameT = intss. getStringExtra("NAMET")
        val desT = intss. getStringExtra("DESCRIT")
        val imgT = intss. getStringExtra("IMGURI")

        nameDetailTextView.text = nameT
        descriptionDetailTextView.text = desT
        teacherDetailImageView.loadImage(imgT)

       val bottomNavigationView = findViewById<BottomNavigationView>(events.app.friendapplication.R.id.navigation)
       bottomNavigationView.selectedItemId = events.app.friendapplication.R.id.home


       bottomNavigationView?.setOnItemSelectedListener { item ->
           when (item.itemId) {
               events.app.friendapplication.R.id.category -> {
                   startActivity(Intent(applicationContext, DecoItemsActivity::class.java))
                   overridePendingTransition(0, 0)
                   return@setOnItemSelectedListener true
               }
               events.app.friendapplication.R.id.home ->  {
                   startActivity(Intent(applicationContext, DecorationActivity::class.java))
                   overridePendingTransition(0, 0)
                   Toast.makeText(this, "Powered by Edimpressions", Toast.LENGTH_SHORT).show()
                   return@setOnItemSelectedListener true
               }

               events.app.friendapplication.R.id.listview -> {
                   startActivity(Intent(applicationContext, SecondActivity::class.java))
                   overridePendingTransition(0, 0)
                   return@setOnItemSelectedListener true
               }
           }
           false
       }

    }
}