package com.example.projekmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DetailLowongan2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detaillowongan2)

        // Setup tombol kembali
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish() // Kembali ke activity sebelumnya
        }

        // Handle register button click
        val registerButton = findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener {
            val intent = Intent(this, Lowongan2Activity::class.java)
            startActivity(intent)
        }

        // Tambahkan logika lain di sini jika diperlukan
    }
}
