package com.macaosoftware.sdui.app

import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import kotlinx.coroutines.delay
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

abstract class JsonObjectHandler(
    private val jsonObject: JsonObject
) {
    private val sduiLocalService = SduiLocalService()

    suspend fun loadChildren() : List<Component> {
        return emptyList()
    }

    suspend fun loadNavItems(): List<NavItem> {
        delay(3000)

        val children = jsonObject.get(
            SduiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map { sduiLocalService.getNavItemOf((it as JsonObject)) }
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