package com.macaosoftware.sdui.app.marketplace.amadeus.auth

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.plugin.AuthPlugin
import com.macaosoftware.sdui.app.plugin.LoginRequest

class AuthViewModel(
    private val authComponent: StateComponent<AuthViewModel>,
    private val authPlugin: AuthPlugin
) : ComponentViewModel() {

    override fun onAttach() {

    }

    override fun onDetach() {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    fun login() {
        authPlugin.login(
            LoginRequest("pablo@gmail.com", "pass123") {
                println("Login Result: $it")
            }
        )
    }

}