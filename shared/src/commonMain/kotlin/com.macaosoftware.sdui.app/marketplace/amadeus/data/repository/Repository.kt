package com.macaosoftware.sdui.app.marketplace.amadeus.data.repository

import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.remote.KtorClientApi

class Repository {

    suspend fun getHotelByCity(cityCode: String): CityCodeHotel{
        return KtorClientApi.getHotelByCity(cityCode)
    }
}