package com.macaosoftware.sdui.app.viewmodel.factory.panelfactory

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.viewmodel.CustomTopAppBarViewModel
import com.macaosoftware.sdui.app.viewmodel.panelViewModel.PanelSettingViewModel

class PanelSettingViewModelFactory() : ComponentViewModelFactory<PanelSettingViewModel> {
    override fun create(component: StateComponent<PanelSettingViewModel>): PanelSettingViewModel {
        return PanelSettingViewModel(component)
    }

}