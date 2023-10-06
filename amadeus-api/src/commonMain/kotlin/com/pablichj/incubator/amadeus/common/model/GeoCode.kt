package com.pablichj.incubator.amadeus.common.model

import kotlinx.serialization.Serializable

@Serializable
data class GeoCode(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)