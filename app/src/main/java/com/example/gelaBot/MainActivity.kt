package com.example.gelaBot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private lateinit var signInButton: Button
    private lateinit var registerButton: TextView
    private lateinit var mogesalmebit: TextView
    private lateinit var rogrobrdz: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        signInButton = findViewById(R.id.signInButton)
        rogrobrdz = findViewById(R.id.signInTextView)
        mogesalmebit = findViewById(R.id.mogesalmebit)
        registerButton = findViewById(R.id.registerButton)

        signInButton.setOnClickListener{

            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

        registerButton.setOnClickListener {

            startActivity(Intent(this, SecondActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

    }

}