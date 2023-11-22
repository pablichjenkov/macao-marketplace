package com.macaosoftware.sdui.app.marketplace.navigator.drawer

import com.macaosoftware.sdui.app.sdui.JsonObjectHandler
import com.macaosoftware.sdui.app.sdui.SduiComponentFactory
import kotlinx.serialization.json.JsonObject

class DrawerSduiHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: SduiComponentFactory
) : JsonObjectHandler(jsonObject, sduiComponentFactory)
