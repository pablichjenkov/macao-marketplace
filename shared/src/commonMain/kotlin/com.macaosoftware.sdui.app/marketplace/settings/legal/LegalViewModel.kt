package com.macaosoftware.sdui.app.marketplace.settings.legal

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.pablichj.incubator.amadeus.Database

class LegalViewModel(
   // private val database: Database,
private val homeComponent: StateComponent<LegalViewModel>
): ComponentViewModel() {

    override fun onAttach() {
        println("LegalViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("LegalViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("LegalViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("LegalViewModel -  onStop() : ")
    }
}
