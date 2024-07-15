package com.example.projekmobile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class Lowongan2Activity : AppCompatActivity() {
    private val REQUEST_CODE_PORTOFOLIO = 1
    private val REQUEST_CODE_CV = 2

    private lateinit var portofolioEditText: EditText
    private lateinit var cvEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var namaLengkapEditText: EditText
    private lateinit var roleEditText: EditText
    private lateinit var daftarButton: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lowongan2)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        portofolioEditText = findViewById(R.id.portofolio)
        cvEditText = findViewById(R.id.cv)
        emailEditText = findViewById(R.id.email)
        namaLengkapEditText = findViewById(R.id.nama_lengkap)
        roleEditText = findViewById(R.id.role)
        daftarButton = findViewById(R.id.daftar_button)

        portofolioEditText.setOnClickListener {
            openFilePicker(REQUEST_CODE_PORTOFOLIO)
        }

        cvEditText.setOnClickListener {
            openFilePicker(REQUEST_CODE_CV)
        }

        daftarButton.setOnClickListener {
            saveUserData()
        }
    }

    private fun openFilePicker(requestCode: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val uri: Uri? = data.data
            when (requestCode) {
                REQUEST_CODE_PORTOFOLIO -> {
                    portofolioEditText.setText(uri?.path ?: "No file selected")
                }
                REQUEST_CODE_CV -> {
                    cvEditText.setText(uri?.path ?: "No file selected")
                }
            }
        }
    }

    private fun saveUserData() {
        val email = emailEditText.text.toString().trim()
        val namaLengkap = namaLengkapEditText.text.toString().trim()
        val portofolio = portofolioEditText.text.toString().trim()
        val cv = cvEditText.text.toString().trim()
        val role = roleEditText.text.toString().trim()

        val user = auth.currentUser
        if (user != null) {
            val uid = user.uid
            val applicationId = UUID.randomUUID().toString()
            val applicationRef = database.reference.child("users").child(uid).child("applications").child(applicationId)

            val applicationMap = mapOf(
                "email" to email,
                "namaLengkap" to namaLengkap,
                "portofolio" to portofolio,
                "cv" to cv,
                "role" to role
            )

            applicationRef.setValue(applicationMap)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Lowongan sudah diinputkan, Terimakasih sudah melamar!", Toast.LENGTH_SHORT).show()
                        // Optionally, start a new activity here
                    } else {
                        Toast.makeText(this, "Database error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }
}
