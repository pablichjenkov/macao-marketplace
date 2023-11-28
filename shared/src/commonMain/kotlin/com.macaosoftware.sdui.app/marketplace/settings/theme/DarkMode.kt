package com.macaosoftware.sdui.app.marketplace.settings.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Theaters
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator


class DarkMode() : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current
        var lselected by remember { mutableStateOf(true) }
        var dselected by remember { mutableStateOf(false) }
        if (dselected) {
            isSystemInDarkTheme()
        } else {
            /* Functionality Added... */
        }

        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
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
                        text = "Theme Settings",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    item {
                        ThemeOptionItem(
                            title = "Light Theme",
                            description = "Switch to the light theme for a brighter experience.",
                            icon = Icons.Default.LightMode,
                            isSelected = lselected,
                            onClick = {
                                lselected = true
                                dselected = false
                            }
                        )
                    }
                    item {
                        ThemeOptionItem(
                            title = "Dark Theme",
                            description = "Switch to the dark theme for a darker and more comfortable viewing.",
                            icon = Icons.Default.DarkMode,
                            isSelected = dselected,
                            onClick = {
                                dselected = true
                                lselected = false
                            }
                        )
                    }
                    item {
                        // Additional content for theme-related information
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Theme Settings",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Customize your app experience by choosing a theme that suits your preference.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.64f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // Additional theme options (you can add more as needed)
                        ThemeOptionItem(
                            title = "Ocean Blue Theme",
                            description = "Dive into a soothing ocean blue theme for a refreshing look.",
                            isSelected = false,
                            icon = Icons.Default.Thermostat,
                            onClick = {
                                // Implement theme change logic
                            }
                        )
                        ThemeOptionItem(
                            title = "Crimson Red Theme",
                            description = "Experience the intensity of a crimson red theme for a vibrant display.",
                            isSelected = false,
                            icon = Icons.Default.Theaters,
                            onClick = {
                                // Implement theme change logic

                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }


    @Composable
    fun ThemeOptionItem(
        title: String, description: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp)
                .border(
                    1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                    shape = MaterialTheme.shapes.medium
                ).clip(MaterialTheme.shapes.medium),

            ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.64f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = isSelected, onClick = null, modifier = Modifier.scale(1.5f)
                    )
                }
            }
        }
    }
}

