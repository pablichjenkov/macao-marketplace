package com.macaosoftware.sdui.app.marketplace.settings.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class LanguageMode() : Screen {
    @Composable
    override fun Content() {
        var selectedLanguage by remember { mutableStateOf("English") }
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
                            text = "Language Preferences",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
                item {
                    LanguageOptionItem(
                        language = "English",
                        isSelected = selectedLanguage == "English",
                        onClick = { selectedLanguage = "English" }
                    )
                }
                item {
                    LanguageOptionItem(
                        language = "Spanish",
                        isSelected = selectedLanguage == "Spanish",
                        onClick = { selectedLanguage = "Spanish" }
                    )
                }
                item {
                    LanguageOptionItem(
                        language = "French",
                        isSelected = selectedLanguage == "French",
                        onClick = { selectedLanguage = "French" }
                    )
                }
                // Add more language options as needed
            }
        }
    }
}

@Composable
fun LanguageOptionItem(
    language: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = language,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
