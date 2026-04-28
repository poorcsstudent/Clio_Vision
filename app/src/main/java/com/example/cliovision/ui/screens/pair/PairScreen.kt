package com.example.cliovision.ui.screens.pair

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cliovision.R
import kotlinx.coroutines.delay

@Composable
fun PairScreen(onPaired: () -> Unit) {
    var isPairing by remember { mutableStateOf(false) }
    var isConnected by remember { mutableStateOf(false) }

    val pulseTransition = rememberInfiniteTransition(label = "pair_pulse")
    val pulseScale by pulseTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isPairing) 1.12f else 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    LaunchedEffect(isPairing) {
        if (isPairing) {
            delay(1800)
            isConnected = true
            delay(700)
            onPaired()
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.clioviz_logo),
                contentDescription = "ClioVision Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .scale(pulseScale)
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(
                        if (isConnected) {
                            MaterialTheme.colorScheme.tertiary
                        } else {
                            MaterialTheme.colorScheme.primary
                        }
                    )
                    .clickable(enabled = !isPairing && !isConnected) {
                        isPairing = true
                    },
                contentAlignment = Alignment.Center
            ) {
                if (isPairing && !isConnected) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(
                        text = if (isConnected) "✓" else "+",
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = when {
                    isConnected -> "Glasses Connected"
                    isPairing -> "Connecting to Meta Glasses..."
                    else -> "Pair Meta Glasses"
                },
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            AnimatedVisibility(visible = isPairing && !isConnected) {
                Text(
                    text = "Searching for nearby device",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            AnimatedVisibility(visible = isConnected) {
                Text(
                    text = "Launching ClioVision hub...",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}