package com.example.e_skillsbintang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_skillsbintang.databinding.DetailEvent3Binding

class DetailEvent3Activity : AppCompatActivity() {

    private lateinit var binding: DetailEvent3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailEvent3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Arahkan button "Daftar" ke halaman "Daftar Seminar"
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, DaftarSeminarActivity::class.java)
            startActivity(intent)
        }
    }
}
