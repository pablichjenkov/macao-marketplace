package com.macaosoftware.sdui.app.data

import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.offers.HotelOffers
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.schedule.ScheduleScreen

object SduiConstants {

    object JsonKeyName {
        const val componentType = "componentType"
        const val children = "children"
    }

    object ComponentType {
        const val BottomNavigation = "BottomNavigation"
        const val Drawer = "Drawer"

        const val HotelDemoComponent = "HotelDemoComponent"
        const val AirportDemoComponent = "AirportDemoComponent"
        const val Setting = "Setting"

        //Simple TopAppBar
        const val SimpleTopAppBar = "SimpleTopAppBar"

        //Home Screen
        const val HomeView = "HomeView"

        //Search Screen
        const val SearchView = "SearchView"

        //Panel Components
        const val Panel = "Panel"
        const val PanelSetting = "PanelSetting"

        //Amadeus Api Screens
        const val HomeScreen = "HomeScreen"
        const val ScheduleScreen = "ScheduleScreen"
        const val HotelOffers = "HotelOffers"
        const val Travel = "TravelScreen"
    }
}
