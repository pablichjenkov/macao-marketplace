package com.macaosoftware.sdui.app.marketplace.amadeus.travel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent
<<<<<<< HEAD
import com.macaosoftware.sdui.app.marketplace.amadeus.search.SearchViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.travel.TravelScreen
=======
>>>>>>> origin

val TravelComponentView: @Composable StateComponent<TravelViewModel>.(
    modifier: Modifier,
    travelViewModel: TravelViewModel
) -> Unit = { modifier: Modifier, travelViewModel: TravelViewModel ->
    Navigator(TravelScreen())
}