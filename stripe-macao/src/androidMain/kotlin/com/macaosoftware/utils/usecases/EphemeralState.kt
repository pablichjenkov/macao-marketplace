package com.macaosoftware.utils.usecases

import com.macaosoftware.data.model.customer.Customer
import com.macaosoftware.data.model.ephemeral.Ephemeral

sealed class EphemeralState {
    object LOADING : EphemeralState()
    data class SUCCESS(val ephemeral: Ephemeral) : EphemeralState()
    data class ERROR(val error: String) : EphemeralState()
}