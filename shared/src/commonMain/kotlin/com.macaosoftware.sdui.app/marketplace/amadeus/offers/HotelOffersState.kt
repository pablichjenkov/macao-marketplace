package com.macaosoftware.sdui.app.marketplace.amadeus.offers

import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels.Hotels

sealed class HotelOffersState {
    object Loading : HotelOffersState()
    data class Success(val hotels: Hotels) : HotelOffersState()
    data class Error(val error: String) : HotelOffersState()
}
