package com.macaosoftware.sdui.app.marketplace.navigator.panel.panelViewModel

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent

class PanelSettingViewModel(
    koinComponent: KoinComponent,
    private val settingsPanelComponent: StateComponent<PanelSettingViewModel>
) : ComponentViewModel(), KoinComponent by koinComponent {
    override fun onAttach() {

    }

    override fun onDetach() {
        println("Component is: ${settingsPanelComponent.id}")
    }

    override fun onStart() {

    }

    override fun onStop() {

    }
}