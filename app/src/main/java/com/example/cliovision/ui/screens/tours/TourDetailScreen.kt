package com.example.cliovision.ui.screens.tours

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cliovision.data.model.Tour

@Composable
fun TourDetailScreen(
    tour: Tour,
    onStartTour: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(tour.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(tour.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${tour.durationMinutes} min • ${tour.stopCount} stops • ${tour.difficulty}")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onStartTour, modifier = Modifier.fillMaxWidth()) {
                Text("Start Tour")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Back")
            }
        }
    }
}