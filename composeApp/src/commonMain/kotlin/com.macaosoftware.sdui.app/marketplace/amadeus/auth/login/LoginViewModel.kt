package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.SignInRequest

class LoginViewModel(
    private val loginComponent: StateComponent<LoginViewModel>,
    private val accountPlugin: AccountPlugin
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

   suspend fun login() {
        accountPlugin.signInWithEmailAndPassword(
            SignInRequest("am.pablo.vc@gmail.com", "123")
        )
    }
}
