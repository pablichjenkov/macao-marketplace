package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginRequest

class LoginViewModel(
    private val loginComponent: StateComponent<LoginViewModel>,
    private val authPlugin: AuthPlugin
): ComponentViewModel() {

    override fun onAttach() {
        println("LoginViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("LoginViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("LoginViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("LoginViewModel -  onStop() : ")
    }

    fun login() {
        authPlugin.login(
            LoginRequest("am.pablo.vc@gmail.com", "123") {
                println("Pablo: Result = $it")
            }
        )
    }
}
