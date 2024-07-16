package com.example.e_skillsbintang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_skillsbintang.databinding.AdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {

    private lateinit var binding: AdminHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AdminHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menggunakan binding untuk mengakses event_card
        binding.eventCard.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }

        // Tambahkan logika lain yang dibutuhkan
    }
}
