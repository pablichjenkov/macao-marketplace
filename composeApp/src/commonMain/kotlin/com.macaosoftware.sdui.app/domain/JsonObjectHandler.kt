package com.macaosoftware.sdui.app.domain

import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.sdui.data.SduiConstants
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

abstract class JsonObjectHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: MacaoComponentFactory
) {
    suspend fun loadChildren(): List<Component> {
        val children = jsonObject.get(
            SduiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map {
            sduiComponentFactory.getComponentInstanceOf((it as JsonObject))
        }
    }

    suspend fun loadNavItems(): List<NavItem> {
        val children = jsonObject.get(
            SduiConstants.JsonKeyName.children
        ) as JsonArray

        return children.map {
            sduiComponentFactory.getNavItemOf((it as JsonObject))
        }
    }
}
