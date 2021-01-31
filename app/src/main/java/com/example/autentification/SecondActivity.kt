package com.example.autentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SecondActivity : AppCompatActivity() {

    private lateinit var SingUpMail: EditText
    private lateinit var mogesalmebit: TextView
    private lateinit var SingUpPassword: EditText
    private lateinit var backButtonFromReg: TextView
    private lateinit var signUpButton: Button
    private lateinit var SingUpPassRepeat: EditText
    private lateinit var resetButtonFromReg: TextView

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        mAuth = FirebaseAuth.getInstance()

        mogesalmebit = findViewById(R.id.mogesalmebit)
        backButtonFromReg = findViewById(R.id.backButtonFromReg)
        resetButtonFromReg = findViewById(R.id.resetButtonFromReg)
        SingUpMail = findViewById(R.id.SingUpMail)
        SingUpPassword = findViewById(R.id.SingUpPassword)
        signUpButton = findViewById(R.id.signUpButton)
        SingUpPassRepeat = findViewById(R.id.SingUpPassRepeat)

        backButtonFromReg.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))

        }

        resetButtonFromReg.setOnClickListener {

            startActivity(Intent(this, ResetFromMainActivity::class.java))

        }

        signUpButton.setOnClickListener {

            val email = SingUpMail.text.toString()
            val password = SingUpPassword.text.toString()
            val confirmedPassword = SingUpPassRepeat.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {

                Toast.makeText(this, "ველები ცარიელია, ხელახლა სცადეთ", Toast.LENGTH_SHORT).show()

            } else if (password != confirmedPassword) {

                Toast.makeText(this, "პაროლის ველები არ ემთხვევა ერთმანეთს", Toast.LENGTH_SHORT).show()

            }

            else if ((email.isNotEmpty() || password.isNotEmpty() || confirmedPassword.isNotEmpty()) && password == confirmedPassword) {

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this, "მოხვდა რაღაც შეცდომა", Toast.LENGTH_SHORT).show()
                            }

                }

            }

        }

    }
}