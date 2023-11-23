package com.macaosoftware.sdui.app.marketplace.amadeus.travel

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class TravelViewModel(
    private val searchViewComponent: StateComponent<TravelViewModel>
): ComponentViewModel(){
    override fun onAttach() {
        println("TravelViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("TravelViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("TravelViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("TravelViewModel -  onStop() : ")
    }
}