package com.macaosoftware.sdui.app.marketplace.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.settings.legal.LegalComponentView
import com.macaosoftware.sdui.app.marketplace.settings.legal.SettingItem


@OptIn(ExperimentalMaterial3Api::class)
val SettingsComponentView: @Composable StateComponent<SettingsViewModel>.(
    modifier: Modifier,
    settingsViewModel: SettingsViewModel
) -> Unit = { modifier: Modifier, settingsViewModel: SettingsViewModel ->


    var darkMode by remember { mutableStateOf(false) }
    var notifications by remember { mutableStateOf(true) }
    var legal by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Setting") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it.calculateTopPadding())
            ) {
                SettingItem(
                    title = "Notifications",
                    description = "Notifications Setting",
                    onClick = { notifications = !notifications },
                    switchValue = notifications,
                    startIcon = Icons.Default.Notifications,
                    endIcon = Icons.Default.KeyboardArrowRight
                )

                SettingItem(
                    title = "Dark Mode",
                    description = "Theme Setting",
                    onClick = { darkMode = !darkMode },
                    switchValue = darkMode,
                    startIcon = Icons.Default.Notifications,
                    endIcon = Icons.Default.KeyboardArrowRight
                )

                SettingItem(
                    title = "Terms Conditions",
                    description = "Terms & Condition",
                    onClick = { LegalComponentView },
                    switchValue = legal,
                    startIcon = Icons.Default.Notifications,
                    endIcon = Icons.Default.KeyboardArrowRight
                )
                // Add more setting items as needed
            }
        }
    )

}

