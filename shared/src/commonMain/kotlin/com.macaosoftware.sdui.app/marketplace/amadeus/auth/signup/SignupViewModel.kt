package com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class SignupViewModel(

    private val loginComponent: StateComponent<SignupViewModel>
): ComponentViewModel() {
    override fun onAttach() {
        println("SignupViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("SignupViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("SignupViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("SignupViewModel -  onStop() : ")
    }
}
