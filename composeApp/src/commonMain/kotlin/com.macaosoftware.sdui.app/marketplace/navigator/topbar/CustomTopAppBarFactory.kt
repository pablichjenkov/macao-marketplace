package com.macaosoftware.sdui.app.marketplace.navigator.topbar

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent

class CustomTopAppBarFactory(
    private val koinComponent: KoinComponent
) : ComponentViewModelFactory<CustomTopAppBarViewModel> {

    override fun create(component: StateComponent<CustomTopAppBarViewModel>): CustomTopAppBarViewModel {
        return CustomTopAppBarViewModel(koinComponent, component)
    }

}