package com.example.e_skillsbintang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e_skillsbintang.databinding.EventBinding

class EventActivity : AppCompatActivity() {

    private lateinit var binding: EventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menggunakan binding untuk mengakses ivBack
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.eventCard.setOnClickListener {
            val intent = Intent(this, DetailEventActivity::class.java)
            startActivity(intent)
        }

        // Arahkan card "Entrepreneur Kreatif" ke halaman "Detail Event 2"
        binding.eventCardEntrepreneurKreatif.setOnClickListener {
            val intent = Intent(this, DetailEvent2Activity::class.java)
            startActivity(intent)
        }

        // Arahkan card "Ayo Belajar Design" ke halaman "Detail Event 3"
        binding.eventCardAyoBelajarDesign.setOnClickListener {
            val intent = Intent(this, DetailEvent3Activity::class.java)
            startActivity(intent)
        }

        // Arahkan card "Strategi Bisnis Pemuka" ke halaman "Detail Event 4"
        binding.eventCardStrategiBisnisPemuka.setOnClickListener {
            val intent = Intent(this, DetailEvent4Activity::class.java)
            startActivity(intent)
        }

        // Navigation bar button listeners
        binding.navigationBarInclude.btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.navigationBarInclude.btnKelasSaya.setOnClickListener {
            // Handle navigation to Kelas Saya Activity
        }

        binding.navigationBarInclude.btnProfile.setOnClickListener {
            // Handle navigation to Profile Activity
        }
    }
}
