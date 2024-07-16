package com.example.e_skillsbintang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_skillsbintang.databinding.DetailEvent4Binding

class DetailEvent4Activity : AppCompatActivity() {

    private lateinit var binding: DetailEvent4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailEvent4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, DaftarSeminarActivity::class.java)
            startActivity(intent)
        }
    }
}
