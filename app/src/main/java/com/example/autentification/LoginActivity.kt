package com.example.autentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var signInTextView: TextView
    private lateinit var textView44: TextView
    private lateinit var resetButton: TextView
    private lateinit var registerButton: TextView
    private lateinit var signInMailEditText: EditText
    private lateinit var backButtonFromLogin: ImageView
    private lateinit var signInPasswordEditText: EditText
    private lateinit var signInButton: Button


    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInTextView = findViewById(R.id.signInTextView)
        signInButton = findViewById(R.id.signInButton)
        backButtonFromLogin = findViewById(R.id.backButtonFromLogin)
        textView44 = findViewById(R.id.textView44reset)
        signInMailEditText = findViewById(R.id.signInMailEditText)
        signInPasswordEditText = findViewById(R.id.signInPasswordEditText)
        resetButton = findViewById(R.id.resetButton)
        registerButton = findViewById(R.id.registerButton)



        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {

            startActivity(Intent(this, ChangeInfoActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()

        }

        backButtonFromLogin.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

        registerButton.setOnClickListener {

            startActivity(Intent(this, SecondActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)


        }

        resetButton.setOnClickListener {

            startActivity(Intent(this, ResetFromMainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

        signInButton.setOnClickListener{

            val email = signInMailEditText.text.toString()
            val password = signInPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "ველები ცარიელია, ხელახლა სცადეთ", Toast.LENGTH_SHORT).show()

            } else {

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            startActivity(Intent(this, ChangeInfoActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "მოხვდა რაღაც შეცდომა", Toast.LENGTH_SHORT).show()
                        }

                    }

            }

        }

    }
}