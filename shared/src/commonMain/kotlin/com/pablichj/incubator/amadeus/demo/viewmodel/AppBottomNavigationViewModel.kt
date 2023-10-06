package com.pablichj.incubator.amadeus.demo.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.core.setNavItems
import com.macaosoftware.component.navbar.BottomNavigationComponent
import com.macaosoftware.component.navbar.BottomNavigationComponentViewModel
import com.macaosoftware.component.navbar.BottomNavigationStatePresenterDefault
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.demo.AirportDemoComponent
import com.pablichj.incubator.amadeus.demo.HotelDemoComponent

class AppBottomNavigationViewModel(
    private val database: Database,
    bottomNavigationComponent: BottomNavigationComponent<AppBottomNavigationViewModel>,
    override val bottomNavigationStatePresenter: BottomNavigationStatePresenterDefault,
) : BottomNavigationComponentViewModel(bottomNavigationComponent) {

    override fun onCreate() {
        bottomNavigationComponent.setNavItems(
            mutableListOf(
                NavItem(
                    label = "Hotel",
                    component = HotelDemoComponent(database),
                    icon = Icons.Default.Home
                ),
                NavItem(
                    label = "Air",
                    component = AirportDemoComponent(database),
                    icon = Icons.Default.Search
                ),
            ),
            selectedIndex = 0
        )
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }
}
