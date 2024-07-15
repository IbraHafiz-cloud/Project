package com.example.projekmobile

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class AdminHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

        val lowonganCard = findViewById<LinearLayout>(R.id.lowongan_card)
        lowonganCard.setOnClickListener {
            val intent = Intent(this, LowonganAdminActivity::class.java)
            startActivity(intent)
        }
    }
}
