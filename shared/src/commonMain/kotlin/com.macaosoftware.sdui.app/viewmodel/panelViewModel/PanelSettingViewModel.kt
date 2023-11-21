package com.macaosoftware.sdui.app.viewmodel.panelViewModel

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class PanelSettingViewModel(
    private val settingsPanelComponent: StateComponent<PanelSettingViewModel>
) : ComponentViewModel() {
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