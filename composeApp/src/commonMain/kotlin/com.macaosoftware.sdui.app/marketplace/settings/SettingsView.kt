package com.macaosoftware.sdui.app.marketplace.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.macaosoftware.component.viewmodel.StateComponent


val SettingsComponentView: @Composable StateComponent<SettingsViewModel>.(
    modifier: Modifier,
    settingsViewModel: SettingsViewModel
) -> Unit = { modifier: Modifier, settingsViewModel: SettingsViewModel ->

    SettingScreen()
}

