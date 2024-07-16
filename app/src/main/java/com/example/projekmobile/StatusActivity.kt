package com.example.projekmobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class StatusActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var statusContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        statusContainer = findViewById(R.id.status_container)

        loadApplicationStatus()
    }

    private fun loadApplicationStatus() {
        val user = auth.currentUser
        if (user != null) {
            val uid = user.uid
            val applicationsRef = database.reference.child("users").child(uid).child("applications")

            applicationsRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    statusContainer.removeAllViews()

                    for (applicationSnapshot in dataSnapshot.children) {
                        val email = applicationSnapshot.child("email").value.toString()
                        val namaLengkap = applicationSnapshot.child("namaLengkap").value.toString()
                        val portofolio = applicationSnapshot.child("portofolio").value.toString()
                        val cv = applicationSnapshot.child("cv").value.toString()
                        val role = applicationSnapshot.child("role").value.toString()
                        val status = applicationSnapshot.child("status").value?.toString() ?: "Pending"

                        addStatusCard(role, namaLengkap, email, portofolio, cv, status)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle possible errors.
                }
            })
        }
    }

    private fun addStatusCard(role: String, namaLengkap: String, email: String, portofolio: String, cv: String, status: String) {
        val layoutInflater = layoutInflater
        val cardView = layoutInflater.inflate(R.layout.card_status, statusContainer, false) as CardView

        val roleTextView = cardView.findViewById<TextView>(R.id.role_text_view)
        val nameTextView = cardView.findViewById<TextView>(R.id.name_text_view)
        val emailTextView = cardView.findViewById<TextView>(R.id.email_text_view)
        val portofolioTextView = cardView.findViewById<TextView>(R.id.portofolio_text_view)
        val cvTextView = cardView.findViewById<TextView>(R.id.cv_text_view)
        val statusTextView = cardView.findViewById<TextView>(R.id.status_text_view)

        roleTextView.text = role
        nameTextView.text = namaLengkap
        emailTextView.text = email
        portofolioTextView.text = portofolio
        cvTextView.text = cv
        statusTextView.text = status

        portofolioTextView.setOnClickListener {
            openFile(portofolio)
        }

        cvTextView.setOnClickListener {
            openFile(cv)
        }

        statusContainer.addView(cardView)
    }

    private fun openFile(fileUrl: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(fileUrl)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            // Set MIME type
            val mimeType = "application/pdf" // Gantilah sesuai jenis file Anda
            intent.setDataAndType(Uri.parse(fileUrl), mimeType)

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Tidak ada aplikasi untuk membuka file ini", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Gagal membuka file: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
