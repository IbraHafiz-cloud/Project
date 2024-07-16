package com.example.e_skillsbintang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_skillsbintang.databinding.DaftarSeminarBinding

class DaftarSeminarActivity : AppCompatActivity() {

    private lateinit var binding: DaftarSeminarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DaftarSeminarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tambahkan logika lain yang dibutuhkan
    }
}
