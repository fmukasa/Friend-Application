package events.app.friendapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {


    private lateinit var log_email: EditText
    private lateinit var log_password: EditText

    private lateinit var fAuth: FirebaseAuth
   // private lateinit var accountBtn: TextView
   // private lateinit var registerBtn: TextView
   // private lateinit var btn_register: Button
    private lateinit var btn_login: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        log_email = findViewById(R.id.log_email)
        log_password = findViewById(R.id.log_password)
         btn_login = findViewById(R.id.btn_login)
        // btn_register = findViewById(R.id.btn_register)
        // accountBtn = findViewById(R.id.accountBtn)
        // registerBtn = findViewById(R.id.registerBtn)
         fAuth = Firebase.auth


        findViewById<Button>(R.id.btn_register).setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        findViewById<TextView>(R.id.accountBtn).setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        findViewById<TextView>(R.id.registerBtn).setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            validateForm()
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun validateForm() {
        val icon = getResources().getDrawable(R.drawable.ic_error)
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(log_email.text.toString().trim()) -> {
                log_email.setError("Please Enter Email", icon)
            }
            TextUtils.isEmpty(log_password.text.toString().trim()) -> {
                log_password.setError("Please Enter Password", icon)
            }
            log_email.text.toString().isNotEmpty() &&
                    log_password.text.toString().isNotEmpty() -> {
                if (log_email.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {

                     firebaseSignUp()
                   // Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    log_email.setError("Please Enter Valid Email Id", icon)
                }
            }

        }
    }

    private fun firebaseSignUp() {
        //btn_login . isEnabled = false
       // btn_login.alpha = 0.5f
        fAuth.signInWithEmailAndPassword(log_email.text.toString(),
            log_password.text.toString()).addOnCompleteListener {
                task ->
            if (task.isSuccessful){
                startActivity(Intent(this@LoginActivity, CountryActivity::class.java))
               // val navHome = activity as FragmentNavigation
               // navHome.navigateFrag(HomeFragment(), addToStack = true)

            }else{
               // btn_login . isEnabled = true
               // btn_login.alpha = 1.0f
                Toast.makeText(this,task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}


       /** btn_login.setOnClickListener {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if (log_email.text.toString().isNullOrEmpty() || log_password.text.toString()
                    .isNullOrEmpty()
            )
            // textViewResponse.text = "Email Address or Password is Not Provided"
                Toast.makeText(
                    this,
                    "Email Address or Password is Not Provided",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                fAuth.signInWithEmailAndPassword(
                    log_email.text.toString(),
                    log_password.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Sign in SuccessFull", Toast.LENGTH_SHORT).show()
                            val user = fAuth.currentUser
                            updateUI(user, log_email.text.toString())
                        } else
                            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT)
                                .show()
                       // startActivity(Intent(this@LoginActivity, SecondActivity::class.java))
                    }
            }

        }
    }
    private fun updateUI(currentUser: FirebaseUser?, toString: String){*/







