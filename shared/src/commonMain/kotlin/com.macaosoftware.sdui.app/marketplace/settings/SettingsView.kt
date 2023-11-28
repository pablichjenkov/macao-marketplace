package com.macaosoftware.sdui.app.marketplace.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent


val SettingsComponentView: @Composable StateComponent<SettingsViewModel>.(
    modifier: Modifier,
    settingsViewModel: SettingsViewModel
) -> Unit = { modifier: Modifier, settingsViewModel: SettingsViewModel ->

    Navigator(SettingScreen())

}

