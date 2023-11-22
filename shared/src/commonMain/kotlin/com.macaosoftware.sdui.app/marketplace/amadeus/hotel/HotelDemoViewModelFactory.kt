package com.macaosoftware.sdui.app.marketplace.amadeus.hotel

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class HotelDemoViewModelFactory(
    private val koinComponent: KoinComponent
) : ComponentViewModelFactory<HotelDemoViewModel> {
    override fun create(
        component: StateComponent<HotelDemoViewModel>
    ): HotelDemoViewModel {
        return HotelDemoViewModel(koinComponent.get())
    }
}
