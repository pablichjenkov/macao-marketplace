package com.macaosoftware.sdui.app.marketplace.amadeus.home

import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing

sealed class HotelState {
    object Loading : HotelState()
    data class Success(val hotelListings: List<HotelListing>) : HotelState()
    data class Error(val error: String) : HotelState()
}
