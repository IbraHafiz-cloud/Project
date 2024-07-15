package com.example.projekmobile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DaftarLowonganActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftarlowongan)

        // Setup tombol kembali
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish() // Kembali ke activity sebelumnya
        }

        // Handle button click to navigate to DetailLowongan1Activity
        val detailButton1 = findViewById<Button>(R.id.detail_button1)
        detailButton1.setOnClickListener {
            val intent = Intent(this, DetailLowongan1Activity::class.java)
            startActivity(intent)
        }

        // Handle button click to navigate to DetailLowongan2Activity
        val detailButton2 = findViewById<Button>(R.id.detail_button2)
        detailButton2.setOnClickListener {
            val intent = Intent(this, DetailLowongan2Activity::class.java)
            startActivity(intent)
        }

        // Setup BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                // Pindah ke HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                return true
            }


            R.id.navigation_profile -> {
                // Pindah ke ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }
}
