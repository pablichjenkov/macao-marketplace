package com.macaosoftware.sdui.app.viewmodel.factory

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.viewmodel.SettingsViewModel

class SettingsViewModelFactory(

) : ComponentViewModelFactory<SettingsViewModel> {

    override fun create(component: StateComponent<SettingsViewModel>): SettingsViewModel {
        return SettingsViewModel(component)
    }
}