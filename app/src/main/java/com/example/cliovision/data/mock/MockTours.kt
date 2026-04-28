package com.example.cliovision.data.mock

import com.example.cliovision.data.model.Stop
import com.example.cliovision.data.model.Tour

object MockTours {

    val tours = listOf(
        Tour(
            id = "1",
            title = "Campus Highlights",
            description = "Explore the most important landmarks on campus.",
            durationMinutes = 35,
            stopCount = 8,
            difficulty = "Easy",
            stops = listOf(
                Stop("1", "Welcome Center", "Start your journey here."),
                Stop("2", "Library", "The central academic hub."),
                Stop("3", "Engineering Hall", "A major engineering building.")
            )
        ),
        Tour(
            id = "2",
            title = "Engineering Tour",
            description = "Explore labs, classrooms, and innovation spaces.",
            durationMinutes = 25,
            stopCount = 5,
            difficulty = "Moderate",
            stops = listOf(
                Stop("1", "Engineering Hall", "Main engineering building."),
                Stop("2", "Innovation Lab", "Student design and maker space.")
            )
        )
    )
}