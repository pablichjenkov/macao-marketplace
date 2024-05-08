package com.macaosoftware.sdui.app.marketplace.amadeus.profile

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin

class ProfileViewModelFactory(
    private val accountPlugin: AccountPlugin
) : ComponentViewModelFactory<ProfileViewModel> {

    override fun create(component: StateComponent<ProfileViewModel>): ProfileViewModel {
        return ProfileViewModel(component, accountPlugin)
    }
}
