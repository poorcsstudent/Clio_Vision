package com.example.cliovision.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProfileScreen(onBack: () -> Unit) {
    var voiceResponses by remember { mutableStateOf(true) }
    var captions by remember { mutableStateOf(true) }
    var accessibilityMode by remember { mutableStateOf(false) }
    var avoidStairs by remember { mutableStateOf(false) }
    var saveTranscripts by remember { mutableStateOf(true) }
    var saveImages by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            SettingsSection(title = "Device") {
                Text("Meta Glasses: Connected")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Battery: --%")
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { }) {
                    Text("Reconnect Glasses")
                }
            }

            SettingsSection(title = "AI Guide") {
                SettingSwitch(
                    title = "Voice Responses",
                    checked = voiceResponses,
                    onCheckedChange = { voiceResponses = it }
                )
                SettingSwitch(
                    title = "Captions / Transcript",
                    checked = captions,
                    onCheckedChange = { captions = it }
                )
            }

            SettingsSection(title = "Tour Preferences") {
                SettingSwitch(
                    title = "Accessibility Mode",
                    checked = accessibilityMode,
                    onCheckedChange = { accessibilityMode = it }
                )
                SettingSwitch(
                    title = "Avoid Stairs",
                    checked = avoidStairs,
                    onCheckedChange = { avoidStairs = it }
                )
            }

            SettingsSection(title = "Privacy") {
                SettingSwitch(
                    title = "Save Transcripts",
                    checked = saveTranscripts,
                    onCheckedChange = { saveTranscripts = it }
                )
                SettingSwitch(
                    title = "Save Captured Images",
                    checked = saveImages,
                    onCheckedChange = { saveImages = it }
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun SettingSwitch(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}