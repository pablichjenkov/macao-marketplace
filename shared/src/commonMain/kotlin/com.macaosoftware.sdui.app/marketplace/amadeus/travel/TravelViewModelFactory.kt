package com.macaosoftware.sdui.app.marketplace.amadeus.travel

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent

class TravelViewModelFactory : ComponentViewModelFactory<TravelViewModel> {
    override fun create(component: StateComponent<TravelViewModel>): TravelViewModel {
        return TravelViewModel(component)
    }
}