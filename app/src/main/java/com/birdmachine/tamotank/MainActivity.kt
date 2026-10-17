// SPDX-License-Identifier: AGPL-3.0-or-later
package com.birdmachine.tamotank

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.birdmachine.tamotank.aquarium.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(
                background = Color(0xFF091D29), surface = Color(0xFF123542),
                primary = Color(0xFF9DE8D1), secondary = Color(0xFFF3B4D0)
            )) {
                Surface(Modifier.fillMaxSize()) { TamoTankHome() }
            }
        }
    }
}

@Composable
private fun TamoTankHome() {
    val context = LocalContext.current
    var portraitTank by remember { mutableStateOf(false) }
    Column(Modifier.safeDrawingPadding().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("TamoTank", style = MaterialTheme.typography.headlineLarge)
        Text("A little pocket of living water.", color = MaterialTheme.colorScheme.secondary)
        Text("Tap the water to feed; swipe to scatter food.")
        // Controls stay above the tank, accessible on phones with damaged lower screens.
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { TamoTankEvents.emit(TamoTankEvent.Tap(.5f, .1f)) }) { Text("Feed") }
            OutlinedButton(onClick = { TamoTankEvents.emit(TamoTankEvent.Bubbles) }) { Text("Bubbles") }
            OutlinedButton(onClick = { TamoTankEvents.emit(TamoTankEvent.Play) }) { Text("Play") }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = !portraitTank, onClick = { portraitTank = false }, label = { Text("Wide tank") })
            FilterChip(selected = portraitTank, onClick = { portraitTank = true }, label = { Text("Tall tank") })
        }
        TamoTankAquarium(Modifier.fillMaxWidth().height(if (portraitTank) 380.dp else 240.dp)
            .clip(RoundedCornerShape(20.dp)).tankTouchObserver()
            .semantics { contentDescription = "Aquarium with seven pet fish. Tap to feed, swipe to scatter food." })
        Text("Meet Pixel, Miso, Dot, Lemon, Guppy, Peach & Mint.",
            style = MaterialTheme.typography.titleMedium)
        Text("Your fish grow as you play. Their habitat saves automatically and rests when the app is hidden.")
        HorizontalDivider()
        Text("Keywi companion", style = MaterialTheme.typography.titleMedium)
        Text("The standalone habitat is ready for independent development. Keywi plugin zones, floating mode, and cross-app connection are planned; this build does not connect to Keywi yet.")
        TextButton(onClick = { context.startActivity(Intent(Intent.ACTION_VIEW,
            Uri.parse("https://github.com/BirdMachine/TamoTank"))) }) { Text("Source & project") }
    }
}
