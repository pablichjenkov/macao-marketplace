package com.macaosoftware.data.model.ephemeral


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ephemeral(
    @SerialName("associated_objects")
    val associatedObjects: List<AssociatedObject>? = null,
    @SerialName("created")
    val created: Int? = null,
    @SerialName("expires")
    val expires: Int? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("livemode")
    val livemode: Boolean? = null,
    @SerialName("object")
    val objectX: String? = null,
    @SerialName("secret")
    val secret: String? = null
)