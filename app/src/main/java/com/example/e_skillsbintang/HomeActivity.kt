package com.example.e_skillsbintang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_skillsbintang.databinding.UserhomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: UserhomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserhomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eventIcon.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

        binding.eventText.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }
    }
}
