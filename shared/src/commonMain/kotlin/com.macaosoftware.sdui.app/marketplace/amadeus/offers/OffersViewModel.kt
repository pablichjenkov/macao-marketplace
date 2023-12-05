package com.macaosoftware.sdui.app.marketplace.amadeus.offers

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class OffersViewModel(

    private val homeComponent: StateComponent<OffersViewModel>
): ComponentViewModel() {
    override fun onAttach() {
        println("ScheduleViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("ScheduleViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("ScheduleViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("ScheduleViewModel -  onStop() : ")
    }
}
