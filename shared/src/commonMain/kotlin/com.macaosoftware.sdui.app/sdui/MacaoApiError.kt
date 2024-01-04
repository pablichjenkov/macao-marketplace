package com.macaosoftware.sdui.app.sdui

import com.macaosoftware.app.util.MacaoError
import kotlinx.serialization.Serializable

@Serializable
data class MacaoApiError(
    val instanceId: Long = -1L,
    val errorCode: Int,
    val errorDescription: String
) : MacaoError
