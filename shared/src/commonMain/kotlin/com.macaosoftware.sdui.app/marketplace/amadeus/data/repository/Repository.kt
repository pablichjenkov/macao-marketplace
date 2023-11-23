package com.macaosoftware.sdui.app.marketplace.amadeus.data.repository

import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers.HotelOffers
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels.Hotels
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel.Travel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.remote.KtorClientApi

class Repository {

    suspend fun getHotelByCity(cityCode: String): CityCodeHotel{
        return KtorClientApi.getHotelByCity(cityCode)
    }
    suspend fun getHotelOffer(hotelId: String): HotelOffers{
        return KtorClientApi.getHotelOffers(hotelId)
    }

    suspend fun getHotels(): Hotels{
        return KtorClientApi.getHotels()
    }

    suspend fun getTravel(): Travel{
        return KtorClientApi.getTravel()
    }
}