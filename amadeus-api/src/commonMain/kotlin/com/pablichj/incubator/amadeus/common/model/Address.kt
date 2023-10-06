package com.pablichj.incubator.amadeus.common.model

@kotlinx.serialization.Serializable
data class Address(
    val cityName: String? = null,
    val cityCode: String? = null,
    val countryName: String? = null,
    val countryCode: String? = null,
    val stateCode: String? = null,
    val regionCode: String? = null,
    val category: String? = null,
    val lines: List<String>? = null,
    val postalCode: String? = null,
    val postalBox: String? = null,
    val text: String? = null,
    val state: String? = null,
)