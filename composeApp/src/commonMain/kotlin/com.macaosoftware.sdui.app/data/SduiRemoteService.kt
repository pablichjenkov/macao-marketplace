package com.macaosoftware.sdui.app.data

import com.macaosoftware.sdui.app.domain.MacaoApiError
import com.macaosoftware.sdui.data.SduiConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonArray

internal class SduiRemoteService(
    private val httpClient: HttpClient
) {

    companion object {
        const val RootComponent = SduiConstants.ComponentType.Drawer
    }

    fun getRootJsonResilience() = buildJsonObject {
        put(
            SduiConstants.JsonKeyName.componentType,
            JsonPrimitive(RootComponent)
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


    suspend fun getRemoteRootComponent(ownerId: String): JsonObject? {
        // val baseUrl = "http://localhost:8080"
        val baseUrl = "https://ktor-gae-401000.appspot.com"
        val resp = httpClient.get(
            urlString = "${baseUrl}/customer-project/json-data/${ownerId}"
        )
        return if (resp.status.isSuccess()) {
            val bodyAsText = resp.bodyAsText()
            // println("bodyText = $bodyText")
            val jsonObject = Json.decodeFromString<JsonObject>(bodyAsText)
            jsonObject
        } else {
            val macaoError = resp.body<MacaoApiError>()
            println("macaoError = $macaoError")
            null
        }
    }

}