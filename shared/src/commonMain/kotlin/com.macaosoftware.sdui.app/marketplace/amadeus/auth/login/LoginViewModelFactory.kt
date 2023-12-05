package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.AuthPlugin

class LoginViewModelFactory(
    private val authPlugin: AuthPlugin
) : ComponentViewModelFactory<LoginViewModel> {
    override fun create(component: StateComponent<LoginViewModel>): LoginViewModel {
        return LoginViewModel(component, authPlugin)
    }
}
