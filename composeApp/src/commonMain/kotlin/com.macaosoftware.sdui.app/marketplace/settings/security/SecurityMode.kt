package com.macaosoftware.sdui.app.marketplace.settings.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import com.macaosoftware.sdui.app.marketplace.settings.account.SwitchSettingItem

@Composable
fun SecuritySettingsScreen() {

    var biometricEnabled by remember { mutableStateOf(false) }
    var passcodeEnabled by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = {
                        // navigator!!.pop()
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                    Text(
                        text = "Security Settings",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            item {
                SwitchSettingItem(
                    title = "Enable Biometric Authentication",
                    isChecked = biometricEnabled,
                    onCheckedChange = { biometricEnabled = it }
                )
            }
            item {
                SwitchSettingItem(
                    title = "Enable Passcode",
                    isChecked = passcodeEnabled,
                    onCheckedChange = { passcodeEnabled = it }
                )
            }
            // Add more security settings as needed
        }
    }
}
