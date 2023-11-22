package com.macaosoftware.sdui.app.marketplace.amadeus.search

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent

class SearchViewModel(
    private val searchViewComponent: StateComponent<SearchViewModel>
): ComponentViewModel(){
    override fun onAttach() {
        println("HomeViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("HomeViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("HomeViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("HomeViewModel -  onStop() : ")
    }
}