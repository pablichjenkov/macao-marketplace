package com.macaosoftware.sdui.app.marketplace.amadeus.offers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent
<<<<<<< HEAD
import com.macaosoftware.sdui.app.marketplace.amadeus.home.HomeViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.offers.HotelOffers
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.schedule.ScheduleScreen
=======
>>>>>>> origin

val OffersComponentView: @Composable StateComponent<OffersViewModel>.(
    modifier: Modifier,
    offersViewModel: OffersViewModel
) -> Unit = { modifier: Modifier, offersViewModel: OffersViewModel ->
    Navigator(HotelOffers())
}