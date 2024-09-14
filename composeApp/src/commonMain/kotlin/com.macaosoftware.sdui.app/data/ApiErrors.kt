package com.macaosoftware.sdui.app.data

import kotlinx.serialization.Serializable

@Serializable
data class GetRemoteRootComponentError(
    val instanceId: Long = -1L,
    val errorCode: Int,
    val errorDescription: String
)
