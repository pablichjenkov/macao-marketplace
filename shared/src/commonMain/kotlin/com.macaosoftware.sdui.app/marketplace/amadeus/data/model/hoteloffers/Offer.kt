package com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hoteloffers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerialName("checkInDate")
    val checkInDate: String,
    @SerialName("checkOutDate")
    val checkOutDate: String,
    @SerialName("guests")
    val guests: Guests,
    @SerialName("id")
    val id: String,
    @SerialName("policies")
    val policies: Policies,
    @SerialName("price")
    val price: Price,
    @SerialName("rateCode")
    val rateCode: String,
    @SerialName("rateFamilyEstimated")
    val rateFamilyEstimated: RateFamilyEstimated,
    @SerialName("room")
    val room: Room,
    @SerialName("self")
    val self: String
)