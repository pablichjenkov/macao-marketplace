package com.macaosoftware.sdui.app.marketplace.settings.security

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.macaosoftware.sdui.app.marketplace.settings.account.SwitchSettingItem

class SecurityMode : Screen {
    @Composable
    override fun Content() {
        var biometricEnabled by remember { mutableStateOf(false) }
        var passcodeEnabled by remember { mutableStateOf(false) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                item {
                    Text(
                        text = "Security Settings",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
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
}
