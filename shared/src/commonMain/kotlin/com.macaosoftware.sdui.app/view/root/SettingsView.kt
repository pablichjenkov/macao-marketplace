package com.macaosoftware.sdui.app.view.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.viewmodel.SettingsViewModel



val SettingsComponentView: @Composable StateComponent<SettingsViewModel>.(
    modifier: Modifier,
    settingsViewModel: SettingsViewModel
) -> Unit = { modifier: Modifier, settingsViewModel: SettingsViewModel ->


    Box(modifier = Modifier.fillMaxSize()) {
        Text("Settings screen")


    }

}