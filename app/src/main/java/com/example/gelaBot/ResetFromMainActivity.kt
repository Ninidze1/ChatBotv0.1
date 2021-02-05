package com.example.gelaBot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class ResetFromMainActivity : AppCompatActivity() {

    private lateinit var ResetFromMainTextView: TextView
    private lateinit var backButtonFromProphRes: ImageView
    private lateinit var ResetMailFromMainEditText: EditText
    private lateinit var resetFromMainButtonSubmit: Button


    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_from_main)

        mAuth = FirebaseAuth.getInstance()


        ResetFromMainTextView = findViewById(R.id.ResetFromMainTextView)
        backButtonFromProphRes = findViewById(R.id.backButtonFromProphRes)
        ResetMailFromMainEditText = findViewById(R.id.ResetMailFromMainEditText)
        resetFromMainButtonSubmit = findViewById(R.id.resetFromMainButtonSubmit)

        backButtonFromProphRes.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))

        }

        resetFromMainButtonSubmit.setOnClickListener {

            val email = ResetMailFromMainEditText.text.toString()

            if (email.isEmpty()) {

                Toast.makeText(this, "ველები ცარიელია, ხელახლა სცადეთ", Toast.LENGTH_SHORT).show()

            } else {

                mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                            Toast.makeText(this, "აღდგენის ბმული გამოგზავნილია თქვენს ფოსტაზე", Toast.LENGTH_LONG).show()

                            finish()
                        } else {
                            Toast.makeText(this, "მოხვდა რაღაც შეცდომა", Toast.LENGTH_SHORT).show()
                        }

                    }

            }

        }

    }

}