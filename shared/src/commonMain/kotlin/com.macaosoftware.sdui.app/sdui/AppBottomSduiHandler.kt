package com.macaosoftware.sdui.app.sdui

import kotlinx.serialization.json.JsonObject

class AppBottomSduiHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: SduiComponentFactory
) : JsonObjectHandler(jsonObject, sduiComponentFactory)
