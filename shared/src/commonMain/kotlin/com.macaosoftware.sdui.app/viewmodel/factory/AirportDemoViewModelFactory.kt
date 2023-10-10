package com.macaosoftware.sdui.app.viewmodel.factory

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.viewmodel.AirportDemoViewModel
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