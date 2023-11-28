package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class LoginViewModel(

    private val loginComponent: StateComponent<LoginViewModel>
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
}
