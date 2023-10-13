package com.macaosoftware.sdui.app.viewmodel

import com.macaosoftware.component.core.setNavItems
import com.macaosoftware.component.drawer.DrawerComponent
import com.macaosoftware.component.drawer.DrawerComponentViewModel
import com.macaosoftware.component.drawer.DrawerStatePresenterDefault
import com.macaosoftware.sdui.app.sdui.DrawerSduiHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrawerViewModel(
    private val sduiHandler: DrawerSduiHandler,
    drawerComponent: DrawerComponent<DrawerViewModel>,
    override val drawerStatePresenter: DrawerStatePresenterDefault,
) : DrawerComponentViewModel(drawerComponent) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onAttach() {
        println("DrawerViewModel::onAttach()")
        coroutineScope.launch {
            val navItems = sduiHandler.loadNavItems()
            drawerComponent.setNavItems(
                navItems = navItems,
                selectedIndex = 0
            )
        }

    }

    override fun onStart() {
        println("DrawerViewModel::onStart()")
    }

    override fun onStop() {
        println("DrawerViewModel::onStop()")
    }

    override fun onDetach() {
        println("DrawerViewModel::onDetach()")
    }
}
