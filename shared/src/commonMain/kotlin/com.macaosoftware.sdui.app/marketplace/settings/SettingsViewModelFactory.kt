package com.macaosoftware.sdui.app.marketplace.settings

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent

class SettingsViewModelFactory(

) : ComponentViewModelFactory<SettingsViewModel> {

    override fun create(component: StateComponent<SettingsViewModel>): SettingsViewModel {
        return SettingsViewModel(component)
    }
}