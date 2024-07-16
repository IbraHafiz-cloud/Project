package com.example.e_skillsbintang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_skillsbintang.databinding.DetailEventBinding

class DetailEventActivity : AppCompatActivity() {

    private lateinit var binding: DetailEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, DaftarSeminarActivity::class.java)
            startActivity(intent)
        }
    }
}
