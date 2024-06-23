package com.macaosoftware.sdui.app.marketplace.navigator.bottomnavigation

import com.macaosoftware.component.bottomnavigation.BottomNavigationComponent
import com.macaosoftware.component.bottomnavigation.BottomNavigationComponentViewModel
import com.macaosoftware.component.bottomnavigation.BottomNavigationStatePresenterDefault
import com.macaosoftware.component.core.setNavItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class BottomNavigationViewModel(
    koinComponent: KoinComponent,
    private val sduiHandler: BottomNavigationSduiHandler,
    bottomNavigationComponent: BottomNavigationComponent<BottomNavigationViewModel>,
    override val bottomNavigationStatePresenter: BottomNavigationStatePresenterDefault,
) : BottomNavigationComponentViewModel(bottomNavigationComponent), KoinComponent by koinComponent {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onAttach() {
        println("BottomNavigationViewModel::onAttach()")
        coroutineScope.launch {
            val navItems = sduiHandler.loadNavItems()
            bottomNavigationComponent.setNavItems(
                navItems = navItems,
                selectedIndex = 0
            )
        }

    }

    override fun onStart() {
        println("BottomNavigationViewModel::onStart()")
    }

    override fun onStop() {
        println("BottomNavigationViewModel::onStop()")
    }

    override fun onDetach() {
        println("BottomNavigationViewModel::onDetach()")
    }
}
