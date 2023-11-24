package com.macaosoftware.sdui.app.marketplace.amadeus.offers

import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers.HotelOffers

sealed class HotelOfferState {
    object Loading : HotelOfferState()
    data class Success(val hotelOffers: HotelOffers) : HotelOfferState()
    data class Error(val error: String) : HotelOfferState()
}
