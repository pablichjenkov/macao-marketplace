package com.macaosoftware.sdui.app.marketplace.navigator.panel.panelViewModel

import com.macaosoftware.component.core.setNavItems
import com.macaosoftware.component.panel.PanelComponent
import com.macaosoftware.component.panel.PanelComponentViewModel
import com.macaosoftware.component.panel.PanelStatePresenterDefault
import com.macaosoftware.sdui.app.marketplace.navigator.panel.PanelSduiHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PanelViewModel(
    private val sduiHandler: PanelSduiHandler,
    panelComponent: PanelComponent<PanelViewModel>,
    override val panelStatePresenter: PanelStatePresenterDefault,
) : PanelComponentViewModel(panelComponent) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onAttach() {
        println("PanelViewModel::onAttach()")
        coroutineScope.launch {
            val navItems = sduiHandler.loadNavItems()
            panelComponent.setNavItems(
                navItems = navItems,
                selectedIndex = 0
            )
        }

    }

    override fun onStart() {
        println("PanelViewModel::onStart()")
    }

    override fun onStop() {
        println("PanelViewModel::onStop()")
    }

    override fun onDetach() {
        println("PanelViewModel::onDetach()")
    }
}
