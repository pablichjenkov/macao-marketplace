package com.macaosoftware.data.model.ephemeral


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssociatedObject(
    @SerialName("id")
    val id: String? = null,
    @SerialName("type")
    val type: String? = null
)