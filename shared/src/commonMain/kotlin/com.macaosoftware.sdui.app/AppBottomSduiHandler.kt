package com.macaosoftware.sdui.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.demo.AirportDemoComponent
import com.pablichj.incubator.amadeus.demo.HotelDemoComponent
import kotlinx.coroutines.delay
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class AppBottomSduiHandler(
    private val jsonObject: JsonObject
) {

    suspend fun loadNavItems(
        database: Database
    ): MutableList<NavItem> {
        delay(3000)

        val children = jsonObject.get(
            SduiConstants.JsonKeyName.children
        ) as JsonArray

        val navItems = mutableListOf<NavItem>()

        children.forEach {
            val childComponentType: String =
                (it as JsonObject)
                    .getValue(SduiConstants.JsonKeyName.componentType)
                    .jsonPrimitive
                    .content
            when (childComponentType) {
                SduiConstants.ComponentType.AirportDemoComponent -> {
                    navItems.add(
                        NavItem(
                            label = "Hotel",
                            component = HotelDemoComponent(database),
                            icon = Icons.Default.Home
                        )
                    )
                }
                SduiConstants.ComponentType.HotelDemoComponent -> {
                    navItems.add(
                        NavItem(
                            label = "Air",
                            component = AirportDemoComponent(database),
                            icon = Icons.Default.Search
                        )
                    )
                }
                else -> {}
            }
        }

        return navItems
    }

}

/*
fun Component.jsonify(
    componentMapper: JsonToComponentMapper
): JsonObject {
    val rootJsonObject = buildJsonObject {
        put("component_type", JsonPrimitive(componentMapper.getType()))
        if (this@jsonify is NavigationComponent) {
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