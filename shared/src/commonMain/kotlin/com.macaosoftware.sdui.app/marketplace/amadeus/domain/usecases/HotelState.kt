package com.macaosoftware.sdui.app.marketplace.amadeus.domain.usecases

import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel

sealed class HotelState {
    object Loading : HotelState()
    data class Success(val cityCodeHotel: CityCodeHotel) : HotelState()
    data class Error(val error: String) : HotelState()
}
