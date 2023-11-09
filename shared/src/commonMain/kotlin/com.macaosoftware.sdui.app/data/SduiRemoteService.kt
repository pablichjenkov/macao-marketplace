package com.macaosoftware.sdui.app.data

import com.macaosoftware.sdui.app.network.httpClient
import com.pablichj.incubator.amadeus.endpoint.airport.AirportAndCitySearchUseCase
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.charsets.Charset
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.internal.decodeStringToJsonTree
import kotlinx.serialization.json.putJsonArray

object SduiRemoteService {

    //const val RootComponent = SduiConstants.ComponentType.BottomNavigation
    const val RootComponent = SduiConstants.ComponentType.Drawer

    fun getRootJson() = buildJsonObject {
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

    suspend fun getRemoteRootComponent(): JsonElement {
        val resp = httpClient.get("https://ktor-gae-401000.appspot.com/customer-project/123")
        // val resp = httpClient.get("http://localhost:8080/macao-demo.json")
        val bodyText = resp.bodyAsText(Charset.forName("UTF-8"))
        println("status = ${resp.status}")
        println("bodyText = $bodyText")
        return resp.body<JsonElement>()
    }

}

/*class MyDeserializationStrategy(
    override val descriptor: SerialDescriptor
) : DeserializationStrategy<JsonElement> {
    override fun deserialize(decoder: Decoder): JsonElement {
        //decoder.
    }
}*/


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
