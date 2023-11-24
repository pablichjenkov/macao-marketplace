package com.macaosoftware.sdui.app.marketplace.amadeus.data.repository

<<<<<<< HEAD
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel
=======
import FormParam
import QueryParam
>>>>>>> origin
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers.HotelOffers
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels.Hotels
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel.Travel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.remote.KtorClientApi
import com.pablichj.incubator.amadeus.common.CallResult
import com.pablichj.incubator.amadeus.demo.BuildConfig
import com.pablichj.incubator.amadeus.endpoint.accesstoken.GetAccessTokenRequest
import com.pablichj.incubator.amadeus.endpoint.accesstoken.GetAccessTokenUseCase
import com.pablichj.incubator.amadeus.endpoint.accesstoken.IAccessTokenDao
import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.AccessToken
import com.pablichj.incubator.amadeus.endpoint.hotels.HotelsByCityUseCase
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelsByCityResponseBody
import kotlinx.coroutines.Dispatchers

class Repository {

    suspend fun getHotelOffer(hotelId: String): HotelOffers {
        return KtorClientApi.getHotelOffers(hotelId)
    }

    suspend fun getHotels(): Hotels {
        return KtorClientApi.getHotels()
    }

    suspend fun getTravel(): Travel {
        return KtorClientApi.getTravel()
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