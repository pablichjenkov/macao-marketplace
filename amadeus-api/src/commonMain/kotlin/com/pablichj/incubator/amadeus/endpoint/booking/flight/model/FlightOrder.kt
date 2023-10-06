package com.pablichj.incubator.amadeus.endpoint.booking.flight.model

/**
 * An Airline object as returned by the Airline Code LookUp API.
 * @see com.amadeus.booking.flightOrder.get
 */
@kotlinx.serialization.Serializable
data class FlightOrder internal constructor(
    val type: String? = null,
    val id: String? = null,
    val queuingOfficeId: String? = null,
    val associatedRecords: List<AssociatedRecord>? = null,
    val travelers: List<Traveler>? = null,
    val flightOffers: List<FlightOfferSearch>? = null
)

@kotlinx.serialization.Serializable
data class AssociatedRecord internal constructor(
    val reference: String? = null,
    val creationDateTime: String? = null,
    val originSystemCode: String? = null,
    val flightOfferId: String? = null
)

@kotlinx.serialization.Serializable
data class Traveler internal constructor(
    val id: String? = null,
    val dateOfBirth: String? = null,
    val name: Name? = null,
    val contact: Contact? = null,
    val documents: List<Document>? = null
)

@kotlinx.serialization.Serializable
data class Name internal constructor(
    val firstName: String? = null,
    val lastName: String? = null
)

@kotlinx.serialization.Serializable
data class Contact internal constructor(
    val phones: List<Phone>? = null
)

@kotlinx.serialization.Serializable
data class Document internal constructor(
    val documentType: String? = null,
    val number: String? = null,
    val expiryDate: String? = null,
    val issuanceCountry: String? = null,
    val nationality: String? = null,
    val holder: Boolean = false
)

@kotlinx.serialization.Serializable
data class Phone internal constructor(
    val countryCallingCode: String? = null,
    val number: String? = null
)