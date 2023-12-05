package com.macaosoftware.sdui.app.marketplace.amadeus.auth

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.AuthPlugin

class AuthViewModelFactory(
    private val authPlugin: AuthPlugin
) : ComponentViewModelFactory<AuthViewModel> {
    override fun create(component: StateComponent<AuthViewModel>): AuthViewModel {
        return AuthViewModel(component, authPlugin)
    }
}
