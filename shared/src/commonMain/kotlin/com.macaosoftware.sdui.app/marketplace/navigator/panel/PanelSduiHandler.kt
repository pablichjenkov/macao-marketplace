package com.macaosoftware.sdui.app.marketplace.navigator.panel

import com.macaosoftware.sdui.app.sdui.JsonObjectHandler
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import kotlinx.serialization.json.JsonObject

class PanelSduiHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: SduiComponentFactory
) : JsonObjectHandler(jsonObject, sduiComponentFactory)
