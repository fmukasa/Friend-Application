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

class RegisterActivity : AppCompatActivity() {


    private lateinit var reg_email: EditText
    private lateinit var reg_password: EditText
    private lateinit var reg_cnf_password: EditText

  //  private lateinit var loginBtn : TextView
    private lateinit var fAuth: FirebaseAuth
   // private lateinit var btn_login_reg: Button
   // private lateinit var alreadyTxView: TextView
    private lateinit var btn_register_reg: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

      // loginBtn = findViewById(R.id.loginBtn)
       // alreadyTxView = findViewById(R.id.alreadyTxView)
        btn_register_reg= findViewById(R.id.btn_register_reg)
        reg_email = findViewById(R.id.reg_email)
        reg_password = findViewById(R.id.reg_password)
        reg_cnf_password = findViewById(R.id.reg_cnf_password)
        fAuth = Firebase.auth


        findViewById<TextView>(R.id.loginBtn).setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        findViewById<TextView>(R.id.alreadyTxView).setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.btn_login_reg).setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            validateEmptyForm()
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun validateEmptyForm() {
        // val icon = AppCompatResources.getDrawable(requireContext(),R.drawable.ic_error)
        val icon = getResources().getDrawable(R.drawable.ic_error)
        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(reg_email.text.toString().trim()) -> {
                reg_email.setError("Please Enter Email", icon)
            }
            TextUtils.isEmpty(reg_password.text.toString().trim()) -> {
                reg_password.setError("Please Enter Password", icon)
            }
            TextUtils.isEmpty(reg_cnf_password.text.toString().trim()) -> {
                reg_cnf_password.setError("Please Enter Password Again", icon)
            }
            reg_email.text.toString().isNotEmpty() &&
                    reg_password.text.toString().isNotEmpty() &&
                    reg_cnf_password.text.toString().isNotEmpty() -> {
                if (reg_email.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    if (reg_password.text.toString().length >= 5) {

                        if (reg_password.text.toString() == reg_cnf_password.text.toString()) {

                            firebaseSignUp()
                            // Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()


                        } else {
                            reg_cnf_password.setError("Password didn't match", icon)
                        }

                    }
                } else {
                    reg_password.setError("Please Enter at least 5 characters", icon)
                }

            }
        }
    }

    private fun firebaseSignUp() {
       // btn_register_reg.isEnabled =false
       // btn_register_reg.alpha = 0.5f
        fAuth.createUserWithEmailAndPassword(reg_email.text.toString(),
            reg_password.text.toString()).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity, CountryActivity::class.java))

               // val navHome = activity as FragmentNavigation
               // navHome.navigateFrag(HomeFragment(), addToStack = true)
            } else {
               // btn_register_reg.isEnabled =true
               // btn_register_reg.alpha = 1.0f
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
       /** alreadyTxView = findViewById(R.id.alreadyTxView)
        loginBtn = findViewById(R.id.loginBtn)
        fAuth = Firebase.auth

        btn_login_reg = findViewById(R.id.btn_login_reg)
        btn_register_reg = findViewById(R.id.btn_register_reg)

        btn_login_reg.setOnClickListener {
        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        btn_register_reg.setOnClickListener {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken,0)
        if (reg_email.text.toString().isNullOrEmpty()||reg_password.text.toString().isNullOrEmpty()
        ||reg_cnf_password.text.toString().isNullOrEmpty())

        // textViewResponse.text = "Email Address or Password is Not Provided"
        Toast.makeText(this, "Email Address or Password is Not Provided", Toast.LENGTH_SHORT).show()
        else {
        fAuth.createUserWithEmailAndPassword(
        reg_email.text.toString(),
        btn_register_reg.text.toString())
        .addOnCompleteListener { task ->
        if (task.isSuccessful){
        Toast.makeText(this, "Register SuccessFull. Email and Password Created",
        Toast.LENGTH_SHORT).show()
        val user = fAuth.currentUser
        updateUI(user)
        }else {
        Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
        updateUI(null)

        startActivity(Intent(this@RegisterActivity, SecondActivity::class.java))
        }

        }
        }
        }
        }

        private fun updateUI(currentUser: FirebaseUser?){

        }*/
