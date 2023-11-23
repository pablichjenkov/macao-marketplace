package com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.home.HomeScreen
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel.AHomeScreenViewModel


val AHomeScreen: @Composable StateComponent<AHomeScreenViewModel>.(
    modifier: Modifier,
    homeScreenViewModel: AHomeScreenViewModel
) -> Unit = { modifier: Modifier, homeScreenViewModel: AHomeScreenViewModel ->
    Navigator(screen = HomeScreen())
}