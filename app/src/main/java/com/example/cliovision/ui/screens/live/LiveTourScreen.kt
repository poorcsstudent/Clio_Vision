package com.example.cliovision.ui.screens.live

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
fun LiveTourScreen(
    tour: Tour,
    onEndTour: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text("Live Tour", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Current Tour: ${tour.title}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("AI narration will appear here.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onEndTour, modifier = Modifier.fillMaxWidth()) {
                Text("End Tour")
            }
        }
    }
}