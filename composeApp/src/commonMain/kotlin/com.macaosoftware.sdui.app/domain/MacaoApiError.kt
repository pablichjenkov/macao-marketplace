package com.macaosoftware.sdui.app.domain

import com.macaosoftware.util.MacaoError
import kotlinx.serialization.Serializable

@Serializable
data class MacaoApiError(
    val instanceId: Long = -1L,
    val errorCode: Int,
    val errorDescription: String
) : MacaoError
