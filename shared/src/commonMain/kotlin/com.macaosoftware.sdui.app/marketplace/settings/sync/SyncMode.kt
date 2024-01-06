package com.macaosoftware.sdui.app.marketplace.settings.sync

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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material.icons.filled.SyncDisabled
import androidx.compose.material.icons.filled.SyncProblem
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class SyncMode() : Screen {
    @Composable
    override fun Content() {
        SyncModeContent()
    }
}

@Composable
fun SyncModeContent() {
    val navigator = LocalNavigator.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Sync Mode Settings")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator!!.pop()
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
                    SyncSettingItem(
                        icon = Icons.Default.Sync,
                        title = "Auto Sync",
                        subtitle = "Automatically sync data in the background",
                        onClick = {
                            // Handle Auto Sync setting click
                        }
                    )
                }
                item {
                    SyncSettingItem(
                        icon = Icons.Default.SyncProblem,
                        title = "Sync Errors",
                        subtitle = "View and resolve sync errors",
                        onClick = {
                            // Handle Sync Errors setting click
                        }
                    )
                }
                item {
                    SyncSettingItem(
                        icon = Icons.Default.SyncDisabled,
                        title = "Disable Sync",
                        subtitle = "Temporarily disable data sync",
                        onClick = {
                            // Handle Disable Sync setting click
                        }
                    )
                }
                item {
                    SyncSettingItem(
                        icon = Icons.Default.SyncAlt,
                        title = "Manual Sync",
                        subtitle = "Manually initiate data sync",
                        onClick = {
                            // Handle Manual Sync setting click
                        }
                    )
                }
                // Add more sync mode settings as needed
            }
        }
    )
}

@Composable
fun SyncSettingItem(
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