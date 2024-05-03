package com.macaosoftware.sdui.app.marketplace.navigator.topbar

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class CustomTopAppBarViewModel(
    private val topAppBarComponent: StateComponent<CustomTopAppBarViewModel>
) : ComponentViewModel() {
    override fun onAttach() {
        println("Custom Top App Bar onAttach: ${topAppBarComponent.id}")
    }

    override fun onDetach() {
        println("Custom Top App Bar onDetach: ${topAppBarComponent.id}")
    }

    override fun onStart() {
        println("Custom Top App Bar onStart: ${topAppBarComponent.id}")
    }

    override fun onStop() {
        println("Custom Top App Bar onStop: ${topAppBarComponent.id}")
    }

}