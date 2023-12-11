package com.macaosoftware.utils.usecases

import com.macaosoftware.data.model.customer.Customer

sealed class CustomerState {
    object LOADING : CustomerState()
    data class SUCCESS(val customer: Customer) : CustomerState()
    data class ERROR(val error: String) : CustomerState()
}