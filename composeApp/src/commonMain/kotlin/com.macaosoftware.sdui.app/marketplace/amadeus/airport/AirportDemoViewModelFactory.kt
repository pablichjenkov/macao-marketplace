package com.macaosoftware.sdui.app.marketplace.amadeus.airport

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class AirportDemoViewModelFactory(
    private val koinComponent: KoinComponent
) : ComponentViewModelFactory<AirportDemoViewModel> {
    override fun create(
        component: StateComponent<AirportDemoViewModel>
    ): AirportDemoViewModel {
        return AirportDemoViewModel(koinComponent.get())
    }
}