package com.macaosoftware.sdui.app.marketplace.navigationbar.topappbar

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent

class CustomTopAppBarFactory() : ComponentViewModelFactory<CustomTopAppBarViewModel> {
    override fun create(component: StateComponent<CustomTopAppBarViewModel>): CustomTopAppBarViewModel {
        return CustomTopAppBarViewModel(component)
    }

}