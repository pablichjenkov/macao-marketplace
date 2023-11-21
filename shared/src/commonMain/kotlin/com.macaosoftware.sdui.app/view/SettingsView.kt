package com.macaosoftware.sdui.app.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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