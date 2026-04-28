package com.example.cliovision.ui.screens.tours

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cliovision.data.model.Tour

@Composable
fun TourListScreen(
    tours: List<Tour>,
    onTourClick: (String) -> Unit
) {
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Available Tours",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            items(tours) { tour ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onTourClick(tour.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(tour.title, style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(tour.description)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("${tour.durationMinutes} min • ${tour.stopCount} stops • ${tour.difficulty}")
                    }
                }
            }
        }
    }
}