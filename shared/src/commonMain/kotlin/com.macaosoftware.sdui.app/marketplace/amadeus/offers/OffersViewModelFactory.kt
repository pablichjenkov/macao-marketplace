package com.macaosoftware.sdui.app.marketplace.amadeus.offers

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class OffersViewModelFactory(

) : ComponentViewModelFactory<OffersViewModel> {
    override fun create(component: StateComponent<OffersViewModel>): OffersViewModel {
        return OffersViewModel(component)
    }
}
