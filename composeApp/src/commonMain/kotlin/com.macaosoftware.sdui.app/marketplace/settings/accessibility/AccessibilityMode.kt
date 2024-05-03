package com.macaosoftware.sdui.app.marketplace.settings.accessibility

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.WheelchairPickup
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AccessibilityModeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Accessibility Settings")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        //navigator!!.pop()
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                item {
                    AccessibilitySettingItem(
                        icon = Icons.Default.Accessibility,
                        title = "VoiceOver",
                        subtitle = "Screen reader for the visually impaired",
                        onClick = {
                        }
                    )
                }
                item {
                    AccessibilitySettingItem(
                        icon = Icons.Default.WheelchairPickup,
                        title = "Wheelchair Support",
                        subtitle = "Enhance support for wheelchair users",
                        onClick = {
                            // Handle Wheelchair Support setting click
                        }
                    )
                }
                item {
                    AccessibilitySettingItem(
                        icon = Icons.Default.VolumeUp,
                        title = "Sound Feedback",
                        subtitle = "Auditory feedback for interactions",
                        onClick = {
                            // Handle Sound Feedback setting click
                        }
                    )
                }
                item {
                    AccessibilitySettingItem(
                        icon = Icons.Default.TextFormat,
                        title = "Text Size",
                        subtitle = "Adjust the size of on-screen text",
                        onClick = {
                            // Handle Text Size setting click
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun AccessibilitySettingItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium)

            }
        }
    }
}