package com.example.e_skillsbintang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_skillsbintang.databinding.ItemParticipantBinding

class ParticipantsAdapter(private val participants: List<Participant>) : RecyclerView.Adapter<ParticipantsAdapter.ParticipantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val binding = ItemParticipantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParticipantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val participant = participants[position]
        holder.binding.tvParticipantName.text = participant.name
        holder.binding.tvParticipantEvent.text = "Mendaftar seminar ${participant.event}"
        holder.binding.tvParticipantDate.text = participant.date
        // holder.binding.imgParticipant.setImageResource(participant.imageResourceId) // Load image if available
    }

    override fun getItemCount(): Int {
        return participants.size
    }

    class ParticipantViewHolder(val binding: ItemParticipantBinding) : RecyclerView.ViewHolder(binding.root)
}



