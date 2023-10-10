package com.macaosoftware.sdui.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.di.SharedKoinComponent
import com.macaosoftware.sdui.app.view.AirportDemoComponentView
import com.macaosoftware.sdui.app.view.HotelDemoComponentView
import com.macaosoftware.sdui.app.viewmodel.AirportDemoViewModel
import com.macaosoftware.sdui.app.viewmodel.HotelDemoViewModel
import com.macaosoftware.sdui.app.viewmodel.factory.AirportDemoViewModelFactory
import com.macaosoftware.sdui.app.viewmodel.factory.HotelDemoViewModelFactory
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.putJsonArray

class SduiService : SharedKoinComponent() {

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

    fun getNavItemOf(
        type: String
    ): NavItem {
        return when (type) {
            SduiConstants.ComponentType.AirportDemoComponent -> {
                NavItem(
                    label = "Hotel",
                    component = getComponentInstanceOf(type),
                    icon = Icons.Default.Home
                )
            }

            SduiConstants.ComponentType.HotelDemoComponent -> {
                NavItem(
                    label = "Air",
                    component = getComponentInstanceOf(type),
                    icon = Icons.Default.Search
                )
            }

            else -> {
                throw Exception("Missing NavItem factory for $type")
            }
        }
    }

    fun getComponentInstanceOf(
        type: String
    ): Component {

        return when (type) {
            SduiConstants.ComponentType.AirportDemoComponent -> {
                StateComponent<HotelDemoViewModel>(
                    viewModelFactory = HotelDemoViewModelFactory(this),
                    content = HotelDemoComponentView
                )
            }

            SduiConstants.ComponentType.HotelDemoComponent -> {
                StateComponent<AirportDemoViewModel>(
                    viewModelFactory = AirportDemoViewModelFactory(this),
                    content = AirportDemoComponentView
                )
            }

            else -> {
                throw Exception("Missing Component factory for $type")
            }
        }

    }

}
