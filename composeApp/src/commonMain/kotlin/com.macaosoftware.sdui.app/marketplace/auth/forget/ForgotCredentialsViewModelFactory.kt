package com.macaosoftware.sdui.app.marketplace.auth.forget

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin

class ForgotCredentialsViewModelFactory(
    private val accountPlugin: AccountPlugin,
    private val viewModelMessageHandler: (ForgotCredentialsViewModelMsg) -> Unit
) : ComponentViewModelFactory<ForgotCredentialsViewModel> {

    override fun create(component: StateComponent<ForgotCredentialsViewModel>): ForgotCredentialsViewModel {
        return ForgotCredentialsViewModel(
            component,
            accountPlugin,
            viewModelMessageHandler
        )
    }
}
