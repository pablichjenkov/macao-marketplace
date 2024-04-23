package com.macaosoftware.sdui.app.marketplace.amadeus.schedule

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class ScheduleViewModel(

    private val homeComponent: StateComponent<ScheduleViewModel>
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
