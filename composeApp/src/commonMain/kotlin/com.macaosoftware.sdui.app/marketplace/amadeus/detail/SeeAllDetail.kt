package com.macaosoftware.sdui.app.marketplace.amadeus.detail

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.SeeAllList
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing

@Composable
fun SeeAllDetail(
    hotelListing: List<HotelListing>,
) {
    SeeAllList(hotelListing = hotelListing)
}