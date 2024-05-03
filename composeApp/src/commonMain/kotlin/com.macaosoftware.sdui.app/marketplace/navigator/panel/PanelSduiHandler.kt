package com.macaosoftware.sdui.app.marketplace.navigator.panel

import com.macaosoftware.sdui.app.domain.JsonObjectHandler
import com.macaosoftware.sdui.app.domain.SduiComponentFactory
import kotlinx.serialization.json.JsonObject

class PanelSduiHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: SduiComponentFactory
) : JsonObjectHandler(jsonObject, sduiComponentFactory)
