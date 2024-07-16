package com.example.projekmobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LowonganAdminActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var applicantsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lowongan_admin)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        applicantsContainer = findViewById(R.id.applicants_container)

        loadApplicants()
    }

    private fun loadApplicants() {
        val usersRef = database.reference.child("users")

        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                applicantsContainer.removeAllViews()

                for (userSnapshot in dataSnapshot.children) {
                    val userId = userSnapshot.key ?: continue
                    val applicationsSnapshot = userSnapshot.child("applications")
                    if (applicationsSnapshot.exists()) {
                        for (applicationSnapshot in applicationsSnapshot.children) {
                            val applicationId = applicationSnapshot.key ?: continue
                            val email = applicationSnapshot.child("email").value.toString()
                            val namaLengkap = applicationSnapshot.child("namaLengkap").value.toString()
                            val portofolio = applicationSnapshot.child("portofolio").value.toString()
                            val cv = applicationSnapshot.child("cv").value.toString()
                            val role = applicationSnapshot.child("role").value.toString()

                            addApplicantCard(userId, applicationId, role, namaLengkap, email, portofolio, cv)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })
    }

    private fun addApplicantCard(userId: String, applicationId: String, role: String, namaLengkap: String, email: String, portofolio: String, cv: String) {
        val layoutInflater = LayoutInflater.from(this)
        val cardView = layoutInflater.inflate(R.layout.card_applicant, applicantsContainer, false) as CardView

        val roleTextView = cardView.findViewById<TextView>(R.id.role_text_view)
        val nameTextView = cardView.findViewById<TextView>(R.id.name_text_view)
        val emailTextView = cardView.findViewById<TextView>(R.id.email_text_view)
        val portofolioTextView = cardView.findViewById<TextView>(R.id.portofolio_text_view)
        val cvTextView = cardView.findViewById<TextView>(R.id.cv_text_view)
        val acceptButton = cardView.findViewById<Button>(R.id.accept_button)
        val rejectButton = cardView.findViewById<Button>(R.id.reject_button)

        roleTextView.text = role
        nameTextView.text = namaLengkap
        emailTextView.text = email
        portofolioTextView.text = portofolio
        cvTextView.text = cv

        portofolioTextView.setOnClickListener {
            openFile(portofolio)
        }

        cvTextView.setOnClickListener {
            openFile(cv)
        }

        acceptButton.setOnClickListener {
            updateApplicationStatus(userId, applicationId, "Diterima")
        }

        rejectButton.setOnClickListener {
            updateApplicationStatus(userId, applicationId, "Ditolak")
        }

        applicantsContainer.addView(cardView)
    }

    private fun updateApplicationStatus(userId: String, applicationId: String, status: String) {
        val applicationRef = database.reference.child("users").child(userId).child("applications").child(applicationId)
        applicationRef.child("status").setValue(status).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Status berhasil diperbarui", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Gagal memperbarui status: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
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
