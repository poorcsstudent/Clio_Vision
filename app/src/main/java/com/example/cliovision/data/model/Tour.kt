package com.example.cliovision.data.model
data class Tour(
    val id: String,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val stopCount: Int,
    val difficulty: String,
    val stops: List<Stop>
)