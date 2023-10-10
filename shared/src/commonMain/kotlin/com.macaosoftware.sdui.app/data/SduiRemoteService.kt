package com.macaosoftware.sdui.app.data

import com.macaosoftware.sdui.app.SduiConstants
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonArray

object SduiRemoteService {

    fun getRootJson() = buildJsonObject {
        put(
            SduiConstants.JsonKeyName.componentType,
            JsonPrimitive(SduiConstants.ComponentType.AppBottomNavigation)
        )
        putJsonArray(SduiConstants.JsonKeyName.children) {
            addJsonObject {
                put(
                    SduiConstants.JsonKeyName.componentType,
                    JsonPrimitive(SduiConstants.ComponentType.AirportDemoComponent)
                )
            }
            addJsonObject {
                put(
                    SduiConstants.JsonKeyName.componentType,
                    JsonPrimitive(SduiConstants.ComponentType.HotelDemoComponent)
                )
            }
        }
    }
}
