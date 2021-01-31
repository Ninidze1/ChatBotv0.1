package com.example.autentification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.autentification.databinding.ActivityChangeInfoBinding
import com.example.autentification.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChangeInfoActivity : AppCompatActivity() {

    private lateinit var imageView2: ImageView
    private lateinit var backButtonFromChangeInfo: ImageView
    private lateinit var userName2: TextView
    private lateinit var resetFromPerson: TextView
    private lateinit var textViewEditProph: TextView
    private lateinit var NameInput: EditText
    private lateinit var UrlInput: EditText
    private lateinit var saveButton: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_info)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("UserInfo")

        NameInput = findViewById(R.id.NameInput)
        textViewEditProph = findViewById(R.id.textViewEditProph)
        resetFromPerson = findViewById(R.id.resetFromPerson)
        backButtonFromChangeInfo = findViewById(R.id.backButtonFromChangeInfo)
        imageView2 = findViewById(R.id.imageView2)
        userName2 = findViewById(R.id.userName2)
        UrlInput = findViewById(R.id.UrlInput)
        saveButton = findViewById(R.id.saveButton)


        resetFromPerson.setOnClickListener {

            startActivity(Intent(this, ResetActivity::class.java))

        }

        backButtonFromChangeInfo.setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))

        }

        saveButton.setOnClickListener {

            val name = NameInput.text.toString()
            val url = UrlInput.text.toString()

            val personInfo = PersonInfo(name, url)

            if (mAuth.currentUser?.uid != null) {

                db.child(mAuth.currentUser?.uid!!).setValue(personInfo)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(this, "ცვლილებები წარმატებით შევიდა ძალაში", Toast.LENGTH_SHORT).show()

                            NameInput.text = null
                            UrlInput.text = null

                        } else {
                            Toast.makeText(this, "მოხვდა რაღაც შეცდომა", Toast.LENGTH_SHORT).show()
                        }

                    }

            }

        }

        if (mAuth.currentUser?.uid != null) {

            db.child(mAuth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val p = snapshot.getValue(PersonInfo::class.java)

                    if (p != null) {

                        userName2.text = p.name

                        Glide.with(this@ChangeInfoActivity)
                            .load(p.url)
                            .centerCrop()
                            .apply(RequestOptions.circleCropTransform())
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(imageView2)

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ChangeInfoActivity, "მოხვდა შეცდომა", Toast.LENGTH_SHORT).show()
                }


            })



        }


    }

}