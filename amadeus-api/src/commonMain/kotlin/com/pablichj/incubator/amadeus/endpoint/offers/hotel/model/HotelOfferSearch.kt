package com.pablichj.incubator.amadeus.endpoint.offers.hotel.model

import com.pablichj.incubator.amadeus.common.model.Address
import kotlinx.serialization.Serializable

/**
 * An HotelOfferSearch object as returned by the HotelOffers API v3.
 * @see com.amadeus.shopping.HotelOffers.get
 */
@Serializable
data class HotelOfferSearch(
    val type: String,
    val hotel: Hotel,
    val available: Boolean = false,
    val offers: List<Offer>,
    val self: String? = null,
) {
    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class Hotel(
        val type: String,
        val hotelId: String,
        val chainCode: String? = null,
        val brandCode: String? = null,
        val dupeId: String? = null,
        val name: String? = null,
        val cityCode: String? = null,
        val address: Address? = null,
        val amenities: List<String>? = null,
        val latitude: Double? = null,
        val longitude: Double? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */

    @Serializable
    class Offer(
        val type: String? = null,
        val id: String,
        val checkInDate: String? = null,
        val checkOutDate: String? = null,
        val roomQuantity: Int? = null,
        val rateCode: String? = null,
        val rateFamilyEstimated: RateFamily? = null,
        val category: String? = null,
        val description: QualifiedFreeText? = null,
        val commission: Commission? = null,
        val boardType: String? = null,
        val room: RoomDetails? = null,
        val guests: Guests? = null,
        val price: HotelPrice,
        val policies: PolicyDetails? = null,
        val self: String? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class RateFamily(
        val code: String? = null,
        val type: String? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class Commission protected constructor() {
        val percentage: String? = null
        val amount: String? = null
        val description: QualifiedFreeText? = null
    }

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class RoomDetails protected constructor() {
        val type: String? = null
        val typeEstimated: EstimatedRoomType? = null
        val description: QualifiedFreeText? = null
    }

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class EstimatedRoomType protected constructor() {
        val category: String? = null
        val beds: Int? = null
        val bedType: String? = null
    }

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class HotelPrice(
        val currency: String? = null,
        val sellingTotal: String? = null,
        val total: String? = null,
        val base: String? = null,
        val taxes: List<HotelTax>? = null,
        val markups: List<Markup>? = null,
        val variations: PriceVariations? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class HotelTax protected constructor() {
        val currency: String? = null
        val amount: String? = null
        val code: String? = null
        val percentage: String? = null
        val included = false
        val description: String? = null
        val pricingFrequency: String? = null
        val pricingMode: String? = null
    }

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class PriceVariations(
        val average: Price? = null,
        val changes: List<PriceVariation>
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class PriceVariation(
        val startDate: String? = null,
        val endDate: String? = null,
        val currency: String? = null,
        val sellingTotal: String? = null,
        val base: String? = null,
        val total: String? = null,
        val markups: List<Markup>? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class Price(
        val currency: String? = null,
        val sellingTotal: String? = null,
        val base: String? = null,
        val total: String? = null,
        val markups: List<Markup>? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class Guests(
        val adults: Int? = null,
        val childAges: List<Int>? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class QualifiedFreeText protected constructor() {
        val lang: String? = null
        val text: String? = null
    }

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class PolicyDetails(
        val paymentType: String? = null,
        val guarantee: GuaranteePolicy? = null,
        val deposit: DepositPolicy? = null,
        val prepay: DepositPolicy? = null,
        val holdTime: HoldPolicy? = null,
        val cancellations: List<CancellationPolicy>,
        val checkInOut: CheckInOutPolicy? = null
    )

    @Serializable
    class DepositPolicy(
        val amount: String? = null,
        val deadline: String? = null,
        val description: QualifiedFreeText? = null,
        val acceptedPayments: PaymentPolicy? = null
    )

    @Serializable
    class HoldPolicy(
        val deadline: String? = null
    )

    @Serializable
    class CheckInOutPolicy(
        val checkIn: String? = null,
        val checkInDescription: QualifiedFreeText? = null,
        val checkOut: String? = null,
        val checkOutDescription: QualifiedFreeText? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class GuaranteePolicy(
        val description: QualifiedFreeText? = null,
        val acceptedPayments: PaymentPolicy? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class CancellationPolicy(
        val type: String? = null,
        val amount: String? = null,
        val numberOfNights: Int? = null,
        val percentage: String? = null,
        val deadline: String? = null,
        val description: QualifiedFreeText? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class PaymentPolicy(
        val creditCards: List<String>? = null,
        val methods: List<String>? = null
    )

    /**
     * An HotelOffer-related object as returned by the HotelOffers API v3.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Serializable
    class Markup(val amount: String? = null)
}
