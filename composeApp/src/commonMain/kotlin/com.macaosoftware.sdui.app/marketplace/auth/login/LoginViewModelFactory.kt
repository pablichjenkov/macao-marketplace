package com.macaosoftware.sdui.app.marketplace.auth.login

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin

class LoginViewModelFactory(
    private val accountPlugin: AccountPlugin
) : ComponentViewModelFactory<LoginViewModel> {
    override fun create(component: StateComponent<LoginViewModel>): LoginViewModel {
        return LoginViewModel(component, accountPlugin)
    }
}
