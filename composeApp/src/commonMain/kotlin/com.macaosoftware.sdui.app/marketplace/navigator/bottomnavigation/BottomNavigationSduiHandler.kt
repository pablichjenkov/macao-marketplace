package com.macaosoftware.sdui.app.marketplace.navigator.bottomnavigation

import com.macaosoftware.sdui.app.domain.JsonObjectHandler
import com.macaosoftware.sdui.app.domain.ViewModelFactory
import kotlinx.serialization.json.JsonObject

class BottomNavigationSduiHandler(
    private val jsonObject: JsonObject,
    private val viewModelFactory: ViewModelFactory
) : JsonObjectHandler(jsonObject, viewModelFactory)
