package com.macaosoftware.sdui.app.marketplace.settings

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class SettingsViewModel(
    private val settingsComponent: StateComponent<SettingsViewModel>
) : ComponentViewModel() {
    override fun onAttach() {

    }

    override fun onDetach() {
        println("Component is: ${settingsComponent.id}")
    }

    override fun onStart() {

    }

    override fun onStop() {

    }
}