package com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent

class SignupViewModelFactory(

) : ComponentViewModelFactory<SignupViewModel> {
    override fun create(component: StateComponent<SignupViewModel>): SignupViewModel {
        return SignupViewModel(component)
    }
}
