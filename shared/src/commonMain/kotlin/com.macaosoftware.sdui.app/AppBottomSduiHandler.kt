package com.macaosoftware.sdui.app

import com.macaosoftware.component.core.NavItem
import kotlinx.coroutines.delay
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class AppBottomSduiHandler(
    private val jsonObject: JsonObject
) {

    private val sduiService = SduiService()

    suspend fun loadNavItems(): MutableList<NavItem> {
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
            navItems.add(
                sduiService.getNavItemOf(childComponentType)
            )
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