package com.macaosoftware.sdui.app.marketplace.amadeus.profile

import com.macaosoftware.component.viewmodel.ComponentViewModelFactory
import com.macaosoftware.component.viewmodel.StateComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ProfileViewModelFactory(

) : ComponentViewModelFactory<ProfileViewModel> {
    override fun create(component: StateComponent<ProfileViewModel>): ProfileViewModel {
        return ProfileViewModel(component)
    }
}
