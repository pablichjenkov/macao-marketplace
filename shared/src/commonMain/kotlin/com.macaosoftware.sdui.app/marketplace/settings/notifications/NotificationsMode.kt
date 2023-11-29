package com.macaosoftware.sdui.app.marketplace.settings.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class NotificationsMode : Screen {
    @Composable
    override fun Content() {
        var emailEnabled by remember { mutableStateOf(true) }
        var pushEnabled by remember { mutableStateOf(true) }
        var soundEnabled by remember { mutableStateOf(true) }
        var vibrationEnabled by remember { mutableStateOf(true) }
        var inAppEnabled by remember { mutableStateOf(false) }

        val navigator = LocalNavigator.current

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = {
                            navigator!!.pop()
                        }) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                        Text(
                            text = "Notification Settings",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
                item {
                    NotificationOptionItem(
                        title = "Email Notifications",
                        description = "Receive important updates via email.",
                        isChecked = emailEnabled,
                        onCheckedChange = { emailEnabled = it }
                    )
                }
                item {
                    NotificationOptionItem(
                        title = "Push Notifications",
                        description = "Get real-time updates with push notifications.",
                        isChecked = pushEnabled,
                        onCheckedChange = { pushEnabled = it }
                    )
                }
                item {
                    NotificationOptionItem(
                        title = "Sound Notifications",
                        description = "Enable sound alerts for notifications.",
                        isChecked = soundEnabled,
                        onCheckedChange = { soundEnabled = it }
                    )
                }
                item {
                    NotificationOptionItem(
                        title = "Vibration",
                        description = "Enable vibration for notifications.",
                        isChecked = vibrationEnabled,
                        onCheckedChange = { vibrationEnabled = it }
                    )
                }
                // Add more notification options as needed
                item {
                    NotificationOptionItem(
                        title = "In-App Notifications",
                        description = "Receive notifications within the app.",
                        isChecked = inAppEnabled,
                        onCheckedChange = { inAppEnabled = it }
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationOptionItem(
    title: String,
    description: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) },
            modifier = Modifier.padding(end = 16.dp)
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f)
            )
        }
    }
}
