package com.macaosoftware.sdui.app.marketplace.auth.signup

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin

class SignupViewModelFactory(
    private val accountPlugin: AccountPlugin
) : ComponentViewModelFactory<SignupViewModel> {

    override fun create(component: StateComponent<SignupViewModel>): SignupViewModel {
        return SignupViewModel(component, accountPlugin)
    }
}
