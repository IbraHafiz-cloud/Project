package com.example.e_skillsbintang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_skillsbintang.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private lateinit var adapter: ParticipantsAdapter
    private val participantList = mutableListOf<Participant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        binding.rvParticipants.layoutManager = LinearLayoutManager(this)

        // Initialize data
        participantList.apply {
            add(Participant("Angela", "Membangun Bisnis Kreatif", "09.03"))
            add(Participant("Shaukia", "Entrepreneur Kreatif", "09.03"))
            add(Participant("Mikhasa", "Ayo Belajar Design", "09.03"))
            add(Participant("Berto", "Strategi Bisnis Pemuka", "09.03"))
            add(Participant("Alkairo", "Membangun Bisnis Kreatif", "09.03"))
            add(Participant("Galleo", "Ayo Belajar Design", "09.03"))
        }

        // Initialize adapter
        adapter = ParticipantsAdapter(participantList)
        binding.rvParticipants.adapter = adapter
    }
}
