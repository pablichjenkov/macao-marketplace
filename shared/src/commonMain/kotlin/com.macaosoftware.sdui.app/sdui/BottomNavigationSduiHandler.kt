package com.macaosoftware.sdui.app.sdui

import kotlinx.serialization.json.JsonObject

class BottomNavigationSduiHandler(
    private val jsonObject: JsonObject,
    private val sduiComponentFactory: SduiComponentFactory
) : JsonObjectHandler(jsonObject, sduiComponentFactory)
