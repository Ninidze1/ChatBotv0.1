package com.example.autentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class ResetActivity : AppCompatActivity() {

    private lateinit var ResetTextView123: TextView
    private lateinit var backButtonFromReset: ImageView
    private lateinit var ResetOldPasswordEditText: EditText
    private lateinit var ResetPasswordEditText: EditText
    private lateinit var resetButtonSubmit: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        mAuth = FirebaseAuth.getInstance()

        ResetTextView123 = findViewById(R.id.ResetTextView123)
        backButtonFromReset = findViewById(R.id.backButtonFromReset)
        ResetOldPasswordEditText = findViewById(R.id.ResetOldPasswordEditText)
        ResetPasswordEditText = findViewById(R.id.ResetPasswordEditText)
        resetButtonSubmit = findViewById(R.id.resetButtonSubmit)

        backButtonFromReset.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

        resetButtonSubmit.setOnClickListener {

            val newPassword = ResetPasswordEditText.text.toString()
            val confirmedPassword = ResetOldPasswordEditText.text.toString()

            if (newPassword.isEmpty() || confirmedPassword.isEmpty()) {

                Toast.makeText(this, "ველი ცარიელია", Toast.LENGTH_SHORT ).show()

            } else if (newPassword != confirmedPassword) {

                Toast.makeText(this, "პაროლის ველები არ ემთხვევა ერთმანეთს", Toast.LENGTH_SHORT).show()

            }

            else if ((newPassword.isNotEmpty() || confirmedPassword.isNotEmpty() && newPassword == confirmedPassword) ) {

                mAuth.currentUser?.updatePassword(newPassword)?.
                addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        startActivity(Intent(this, ChangeInfoActivity::class.java))
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        finish()

                    } else {

                        Toast.makeText(this, "დაფიქსირდა შეცდომა", Toast.LENGTH_SHORT ).show()


                    }

                }

            }

        }

    }
}