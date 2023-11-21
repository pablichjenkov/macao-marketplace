package com.macaosoftware.sdui.app.viewmodel.factory

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.viewmodel.CustomTopAppBarViewModel

class CustomTopAppBarFactory() : ComponentViewModelFactory<CustomTopAppBarViewModel> {
    override fun create(component: StateComponent<CustomTopAppBarViewModel>): CustomTopAppBarViewModel {
        return CustomTopAppBarViewModel(component)
    }

}