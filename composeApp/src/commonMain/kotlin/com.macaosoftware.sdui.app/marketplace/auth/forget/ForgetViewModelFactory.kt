package com.macaosoftware.sdui.app.marketplace.auth.forget

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin

class ForgetViewModelFactory(
    private val accountPlugin: AccountPlugin
) : ComponentViewModelFactory<ForgetViewModel> {
    override fun create(component: StateComponent<ForgetViewModel>): ForgetViewModel {
        return ForgetViewModel(component, accountPlugin)
    }
}
