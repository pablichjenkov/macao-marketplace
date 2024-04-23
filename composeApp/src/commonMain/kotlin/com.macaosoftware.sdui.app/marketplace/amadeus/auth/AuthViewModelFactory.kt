package com.macaosoftware.sdui.app.marketplace.amadeus.auth

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin

class AuthViewModelFactory(
    private val accountPlugin: AccountPlugin
) : ComponentViewModelFactory<AuthViewModel> {
    override fun create(component: StateComponent<AuthViewModel>): AuthViewModel {
        return AuthViewModel(component, accountPlugin)
    }
}
