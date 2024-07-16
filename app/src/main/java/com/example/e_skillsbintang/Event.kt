package com.example.e_skillsbintang

data class Event(
    val imageResId: Int, // Gunakan Int jika Anda mengacu pada ID sumber daya gambar
    val name: String,
    val description: String,
    val speaker: String,
    val date: String
)
