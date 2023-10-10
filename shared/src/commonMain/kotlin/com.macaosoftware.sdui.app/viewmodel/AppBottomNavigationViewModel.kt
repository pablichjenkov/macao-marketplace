package com.macaosoftware.sdui.app.viewmodel

import com.macaosoftware.component.core.setNavItems
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentViewModel
import com.macaosoftware.component.navbar.BottomNavigationStatePresenterDefault
import com.macaosoftware.sdui.app.AppBottomSduiHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppBottomNavigationViewModel(
    private val sduiHandler: AppBottomSduiHandler,
    bottomNavigationComponent: BottomNavigationComponent<AppBottomNavigationViewModel>,
    override val bottomNavigationStatePresenter: BottomNavigationStatePresenterDefault,
) : BottomNavigationComponentViewModel(bottomNavigationComponent) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        coroutineScope.launch {
            val navItems = sduiHandler.loadNavItems()
            bottomNavigationComponent.setNavItems(
                navItems = navItems,
                selectedIndex = 0
            )
        }

    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }
}
