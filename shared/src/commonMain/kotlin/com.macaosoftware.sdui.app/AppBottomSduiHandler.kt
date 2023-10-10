package com.macaosoftware.sdui.app

import kotlinx.serialization.json.JsonObject

class AppBottomSduiHandler(
    private val jsonObject: JsonObject
) : JsonObjectHandler(jsonObject)
