package com.example.projekmobile

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private lateinit var tvNamaLengkap: TextView
    private lateinit var tvEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize Firebase Auth and Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // Initialize views
        tvNamaLengkap = findViewById(R.id.name_edit_text)
        tvEmail = findViewById(R.id.email_edit_text)

        // Get user data from Firebase
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userRef = database.reference.child("users").child(currentUser.uid)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val namaLengkap = dataSnapshot.child("namaLengkap").getValue(String::class.java)
                    val email = dataSnapshot.child("email").getValue(String::class.java)

                    tvNamaLengkap.text = namaLengkap
                    tvEmail.text = email
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@ProfileActivity, "Failed to load user data", Toast.LENGTH_SHORT).show()
                }
            })
        }

        val sertifikatTextView = findViewById<TextView>(R.id.sertifikat_value)
        sertifikatTextView.setOnClickListener {
            val intent = Intent(this, SertifikatActivity::class.java)
            startActivity(intent)
        }
    }
}
