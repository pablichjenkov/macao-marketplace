package com.macaosoftware.sdui.app.marketplace.amadeus.auth.forget

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent

class ForgetViewModelFactory(

) : ComponentViewModelFactory<ForgetViewModel> {
    override fun create(component: StateComponent<ForgetViewModel>): ForgetViewModel {
        return ForgetViewModel(component)
    }
}
