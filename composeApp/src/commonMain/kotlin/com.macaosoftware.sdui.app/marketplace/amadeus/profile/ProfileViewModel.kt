package com.macaosoftware.sdui.app.marketplace.amadeus.profile

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class ProfileViewModel(

    private val homeComponent: StateComponent<ProfileViewModel>
): ComponentViewModel() {
    override fun onAttach() {
        println("ProfileViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("ProfileViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("ProfileViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("ProfileViewModel -  onStop() : ")
    }
}
