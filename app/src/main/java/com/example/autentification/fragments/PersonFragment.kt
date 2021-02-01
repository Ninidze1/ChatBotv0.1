package com.example.autentification.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.autentification.MainActivity
import com.example.autentification.PersonInfo
import com.example.autentification.R
import com.example.autentification.ResetActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PersonFragment: Fragment(R.layout.fragment_person) {

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

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("UserInfo")

        NameInput = view.findViewById(R.id.NameInput)
        textViewEditProph = view.findViewById(R.id.textViewEditProph)
        resetFromPerson = view.findViewById(R.id.resetFromPerson)
        backButtonFromChangeInfo = view.findViewById(R.id.backButtonFromChangeInfo)
        imageView2 = view.findViewById(R.id.imageView2)
        userName2 = view.findViewById(R.id.userName2)
        UrlInput = view.findViewById(R.id.UrlInput)
        saveButton = view.findViewById(R.id.saveButton)
        navController = Navigation.findNavController(view)


        resetFromPerson.setOnClickListener {
            requireActivity().run {

                startActivity(Intent(this, ResetActivity::class.java))
            }

        }


        backButtonFromChangeInfo.setOnClickListener {

            requireActivity().run {

                startActivity(Intent(this, MainActivity::class.java))
            }

            mAuth.signOut()

        }

        saveButton.setOnClickListener {


            val name = NameInput.text.toString()
            val url = UrlInput.text.toString()

            val personInfo = PersonInfo(name, url)

            if (mAuth.currentUser?.uid != null) {

                db.child(mAuth.currentUser?.uid!!).setValue(personInfo)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(activity, "ცვლილებები წარმატებით შევიდა ძალაში", Toast.LENGTH_SHORT).show()

                            NameInput.text = null
                            UrlInput.text = null

                        } else {
                            Toast.makeText(activity, "მოხვდა რაღაც შეცდომა", Toast.LENGTH_SHORT).show()
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

                        Glide.with(this@PersonFragment)
                            .load(p.url)
                            .centerCrop()
                            .apply(RequestOptions.circleCropTransform())
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(imageView2)

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "მოხვდა შეცდომა", Toast.LENGTH_SHORT).show()
                }


            })



        }



    }

}