package com.macaosoftware.sdui.app.sdui

import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.sdui.app.data.SduiConstants
import kotlinx.coroutines.delay
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

abstract class JsonObjectHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: SduiComponentFactory
) {
    suspend fun loadChildren(): List<Component> {
        delay(3000)

        val children = jsonObject.get(
            SduiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map {
            sduiComponentFactory.getComponentInstanceOf((it as JsonObject))
        }
    }

    suspend fun loadNavItems(): List<NavItem> {
        delay(3000)

        val children = jsonObject.get(
            SduiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map {
            sduiComponentFactory.getNavItemOf((it as JsonObject))
        }
    }
}
