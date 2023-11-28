package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent

class LoginViewModelFactory(

) : ComponentViewModelFactory<LoginViewModel> {
    override fun create(component: StateComponent<LoginViewModel>): LoginViewModel {
        return LoginViewModel(component)
    }
}
