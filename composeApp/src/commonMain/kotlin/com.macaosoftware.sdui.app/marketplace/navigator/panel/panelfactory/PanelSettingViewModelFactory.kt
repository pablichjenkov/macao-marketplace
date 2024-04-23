package com.macaosoftware.sdui.app.marketplace.navigator.panel.panelfactory

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.navigator.panel.panelViewModel.PanelSettingViewModel

class PanelSettingViewModelFactory() : ComponentViewModelFactory<PanelSettingViewModel> {
    override fun create(component: StateComponent<PanelSettingViewModel>): PanelSettingViewModel {
        return PanelSettingViewModel(component)
    }

}