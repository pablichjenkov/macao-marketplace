package com.macaosoftware.sdui.app.marketplace.amadeus.domain.usecases

import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers.HotelOffers

sealed class HotelOfferState {
    object Loading : HotelOfferState()
    data class Success(val hotelOffers: HotelOffers) : HotelOfferState()
    data class Error(val error: String) : HotelOfferState()
}
