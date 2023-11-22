package com.macaosoftware.sdui.app.marketplace.amadeus.home

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class HomeViewModelFactory(
    private val koinComponent: KoinComponent
) : ComponentViewModelFactory<HomeViewModel> {
    override fun create(component: StateComponent<HomeViewModel>): HomeViewModel {
        return HomeViewModel(koinComponent.get())
    }
}
