package com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel.AHomeScreenViewModel

class AHomeViewModelFactory(

) : ComponentViewModelFactory<AHomeScreenViewModel> {
    override fun create(component: StateComponent<AHomeScreenViewModel>): AHomeScreenViewModel {
        return AHomeScreenViewModel(component)
    }
}
