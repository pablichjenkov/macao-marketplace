package com.macaosoftware.sdui.app.view.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.home.HomeScreen
import com.macaosoftware.sdui.app.viewmodel.amadeus.viewmodel.AHomeScreenViewModel


val AHomeScreen: @Composable StateComponent<AHomeScreenViewModel>.(
    modifier: Modifier,
    homeScreenViewModel: AHomeScreenViewModel
) -> Unit = { modifier: Modifier, homeScreenViewModel: AHomeScreenViewModel ->
    HomeScreen()
}