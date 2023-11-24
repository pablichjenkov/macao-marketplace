package com.macaosoftware.sdui.app.marketplace.amadeus.offers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent

val OffersComponentView: @Composable StateComponent<OffersViewModel>.(
    modifier: Modifier,
    offersViewModel: OffersViewModel
) -> Unit = { modifier: Modifier, offersViewModel: OffersViewModel ->
    Navigator(HotelOffers())
}