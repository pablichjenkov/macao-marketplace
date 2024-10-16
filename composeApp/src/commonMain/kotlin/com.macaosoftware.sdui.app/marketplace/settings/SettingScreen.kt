package com.macaosoftware.sdui.app.marketplace.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AppsOutage
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.macaosoftware.sdui.app.marketplace.settings.legal.SettingItem

@Composable
fun SettingScreen() {

        var darkMode by remember { mutableStateOf(false) }
        var notifications by remember { mutableStateOf(true) }
        var legal by remember { mutableStateOf(false) }
        var info by remember { mutableStateOf(false) }

        // Additional States
        var accountSettings by remember { mutableStateOf(false) }
        var languagePreferences by remember { mutableStateOf(false) }
        var privacySettings by remember { mutableStateOf(false) }
        var securitySettings by remember { mutableStateOf(false) }
        var notificationPreferences by remember { mutableStateOf(false) }
        var accessibilitySettings by remember { mutableStateOf(false) }
        var syncSettings by remember { mutableStateOf(false) }
        var aboutTheApp by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Setting") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    ),
                    actions = {
                        IconButton(onClick = { info = !info }) {
                            Icon(
                                imageVector = Icons.Default.Info, contentDescription = null,
                                tint = Color.White
                            )
                        }
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = it.calculateTopPadding())
                ) {
                    SettingItem(
                        title = "Notifications",
                        description = "Notifications Setting",
                        onClick = { /*navigator!!.push(NotificationsMode())*/ },
                        switchValue = notifications,
                        startIcon = Icons.Default.Notifications,
                        endIcon = Icons.Default.KeyboardArrowRight
                    )

                    SettingItem(
                        title = "Dark Mode",
                        description = "Theme Setting",
                        onClick = {  },
                        switchValue = darkMode,
                        startIcon = Icons.Default.DarkMode,
                        endIcon = Icons.Default.KeyboardArrowRight
                    )

                    SettingItem(
                        title = "Terms Conditions",
                        description = "Terms & Condition",
                        onClick = { /*navigator!!.push(LegalScreen())*/ },
                        switchValue = legal,
                        startIcon = Icons.Default.PrivacyTip,
                        endIcon = Icons.Default.KeyboardArrowRight
                    )

                    // Additional Setting Items
                    SettingItem(
                        title = "Account Settings",
                        description = "Manage your account details.",
                        onClick = { /*navigator!!.push(AccountMode())*/ },
                        endIcon = Icons.Default.KeyboardArrowRight,
                        startIcon = Icons.Default.AccountBox
                    )

                    SettingItem(
                        title = "Language Preferences",
                        description = "Choose your preferred language.",
                        onClick = { /*navigator!!.push(LanguageMode())*/ },
                        endIcon = Icons.Default.KeyboardArrowRight,
                        startIcon = Icons.Default.Language
                    )

                    SettingItem(
                        title = "Privacy Settings",
                        description = "Customize privacy preferences.",
                        onClick = {
                            //navigator!!.push(PrivacyMode())
                        },
                        endIcon = Icons.Default.KeyboardArrowRight,
                        startIcon = Icons.Default.PrivacyTip
                    )

                    SettingItem(
                        title = "Security Settings",
                        description = "Enhance account security.",
                        onClick = { /*navigator!!.push(SecurityMode())*/ },
                        endIcon = Icons.Default.KeyboardArrowRight,
                        startIcon = Icons.Default.Security
                    )

                    SettingItem(
                        title = "Notification Preferences",
                        description = "Customize notification settings.",
                        onClick = { notificationPreferences = !notificationPreferences },
                        endIcon = Icons.Default.KeyboardArrowRight,
                        startIcon = Icons.Default.Notifications
                    )

                    SettingItem(
                        title = "Accessibility Settings",
                        description = "Adjust accessibility features.",
                        onClick = { /*navigator!!.push(AccessibilityMode())*/ },
                        endIcon = Icons.Default.KeyboardArrowRight,
                        startIcon = Icons.Default.Accessibility
                    )

                    SettingItem(
                        title = "Sync Settings",
                        description = "Manage synchronization options.",
                        onClick = { /*navigator!!.push(SyncMode())*/ },
                        endIcon = Icons.Default.KeyboardArrowRight,
                        startIcon = Icons.Default.Sync
                    )

                    SettingItem(
                        title = "About the App",
                        description = "Get information about the app.",
                        onClick = { /*navigator!!.push(AboutMode())*/ },
                        endIcon = Icons.Default.KeyboardArrowRight,
                        startIcon = Icons.Default.AppsOutage
                    )
                }
                if (info) {
                    AlertDialog(
                        onDismissRequest = { info = false },
                        confirmButton = {},
                        modifier = Modifier.fillMaxWidth(),
                        dismissButton = null,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null
                            )
                        },
                        title = { Text(text = "App Information") },
                        text = {
                            Column {
                                Text(text = "Welcome to [Your App Name]!")
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Version: 1.0.0",
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Release Date: January 1, 2023",
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Description", fontWeight = FontWeight.Bold)
                                Text(
                                    text = "Explore the exciting features of our app that make your daily tasks easier. [Your App Name] is designed with a focus on simplicity, efficiency, and a delightful user experience.",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Contact Us:",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "If you have any questions or feedback, feel free to reach out to our support team at support@[yourcompany].com.",
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
        )
    }
