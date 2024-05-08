package com.macaosoftware.sdui.app.marketplace.amadeus.profile

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.UserData

class ProfileViewModel(
    private val stateComponent: StateComponent<ProfileViewModel>,
    val accountPlugin: AccountPlugin
): ComponentViewModel() {

    override fun onAttach() {
        println("ProfileViewModel -  onAttach() : ")
    }

    override fun onStart() {
        println("ProfileViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("ProfileViewModel -  onStop() : ")
    }

    override fun onDetach() {
        println("ProfileViewModel -  onDetach() : ")
    }

    fun getUser(): UserData {

        return UserData(
            "1234",
            "fake@gmail.com",
            "Test User"
        )
    }
}
