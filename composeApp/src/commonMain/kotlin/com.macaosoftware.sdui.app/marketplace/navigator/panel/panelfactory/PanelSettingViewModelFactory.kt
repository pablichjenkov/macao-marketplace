package com.macaosoftware.sdui.app.marketplace.navigator.panel.panelfactory

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.navigator.panel.panelViewModel.PanelSettingViewModel
import org.koin.core.component.KoinComponent

class PanelSettingViewModelFactory(
    private val koinComponent: KoinComponent
) : ComponentViewModelFactory<PanelSettingViewModel> {

    override fun create(component: StateComponent<PanelSettingViewModel>): PanelSettingViewModel {
        return PanelSettingViewModel(koinComponent, component)
    }
}
