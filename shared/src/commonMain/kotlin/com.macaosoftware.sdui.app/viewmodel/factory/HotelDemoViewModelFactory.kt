package com.macaosoftware.sdui.app.viewmodel.factory

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.viewmodel.HotelDemoViewModel
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
