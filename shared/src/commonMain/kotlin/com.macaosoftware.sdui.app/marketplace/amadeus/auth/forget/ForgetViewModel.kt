package com.macaosoftware.sdui.app.marketplace.amadeus.auth.forget

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class ForgetViewModel(

    private val loginComponent: StateComponent<ForgetViewModel>
): ComponentViewModel() {
    override fun onAttach() {
        println("ForgetViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("ForgetViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("ForgetViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("ForgetViewModel -  onStop() : ")
    }
}
