package com.pablichj.incubator.amadeus.endpoint.hotels.model

import com.pablichj.incubator.amadeus.common.model.Address
import com.pablichj.incubator.amadeus.common.model.Distance
import com.pablichj.incubator.amadeus.common.model.GeoCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotelListing(
    val chainCode: String? = null,
    val iataCode: String? = null,
    val giataId: String? = null,
    val dupeId: Long? = null,
    val name: String? = null,
    val hotelId: String? = null,
    val geoCode: GeoCode? = null,
    val address: Address? = null,
    val distance: Distance? = null,
    val lastUpdate: String? = null,
    val subtype: String? = null,
    val timeZoneName: String? = null,
    val googlePlaceId: String? = null,
    val openjetAirportId: String? = null,
    val uicCode: String? = null
)