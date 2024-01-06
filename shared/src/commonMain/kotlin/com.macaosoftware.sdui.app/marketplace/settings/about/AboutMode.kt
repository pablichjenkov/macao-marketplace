package com.macaosoftware.sdui.app.marketplace.settings.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.sdui.app.theme.AppTheme

class AboutMode() : Screen {
    @Composable
    override fun Content() {
        AboutModeContent()
    }
}

@Composable
fun AboutModeContent() {
    var isUpdateAvailable by remember { mutableStateOf(false) }
    val navigator = LocalNavigator.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "About The App")
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it.calculateTopPadding()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App Logo
//                Image(
//                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
//                    contentDescription = null,
//                    modifier = Modifier.size(100.dp)
//                )

                // App Information
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "My Awesome App", style = MaterialTheme.typography.headlineSmall)
                    Text(text = "Version 1.0.0", style = MaterialTheme.typography.bodyLarge)
                }

                // Developer Information
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Developed by: ", style = MaterialTheme.typography.labelLarge)
                    Text(text = "Your Name", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Contact: your.email@example.com",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // App Update
                OutlinedButton(
                    onClick = {
                        isUpdateAvailable = !isUpdateAvailable
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Update, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Check for Updates")
                    }
                }

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                )

                // Settings
                OutlinedButton(
                    onClick = {
                        // Handle settings
                        // You can navigate to the settings screen
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "App Settings")
                    }
                }

                if (isUpdateAvailable) {
                    AlertDialog(
                        onDismissRequest = { isUpdateAvailable = false },
                        confirmButton = { },
                        dismissButton = null,
                        modifier = Modifier.fillMaxWidth(),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Update,
                                contentDescription = null
                            )
                        },
                        title = { Text(text = "App Update Available") },
                        text = {
                            Column {
                                Text(text = "A new version of [Your App Name] is available!")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Version: 2.0.0",
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Release Date: February 1, 2023",
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "What's New", fontWeight = FontWeight.Bold)
                                Text(
                                    text = "- Exciting new features\n- Performance improvements\n- Bug fixes",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                        },
                        shape = MaterialTheme.shapes.medium,
                        properties = DialogProperties(
                            dismissOnBackPress = true,
                            dismissOnClickOutside = true,
                            usePlatformDefaultWidth = true
                        )
                    )
                }

            }
        }
    )
}