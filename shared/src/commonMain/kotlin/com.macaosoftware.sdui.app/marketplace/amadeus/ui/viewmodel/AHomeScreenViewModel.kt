package com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class AHomeScreenViewModel(
  private val homeComponent: StateComponent<AHomeScreenViewModel>
): ComponentViewModel() {
    override fun onAttach() {
        println("AHomeScreenViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("AHomeScreenViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("AHomeScreenViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("AHomeScreenViewModel -  onStop() : ")
    }

}
