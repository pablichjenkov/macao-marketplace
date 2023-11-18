package com.macaosoftware.sdui.app.data

import com.macaosoftware.sdui.app.domain.MacaoApiError
import com.macaosoftware.sdui.app.http.httpClient
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

object SduiRemoteService {

    const val RootComponent = SduiConstants.ComponentType.Drawer

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

/* Convert from Component tree to JsonObject tree
fun Component.jsonify(
    componentMapper: JsonToComponentMapper
): JsonObject {
    val rootJsonObject = buildJsonObject {
        put("component_type", JsonPrimitive(componentMapper.getType()))
        if (this@jsonify is ComponentWithChildren) {
            val children = mutableListOf<JsonObject>()
            this@jsonify.childComponents.forEach {
                val mapper = componentTypeRegistry.getValue(it.id?:"")
                val childJson = it.jsonify(mapper)
                children.add(childJson)
            }
            putJsonArray("children") {
                for (child in children) add(child)
            }
        }

    }

    return rootJsonObject
}
*/
