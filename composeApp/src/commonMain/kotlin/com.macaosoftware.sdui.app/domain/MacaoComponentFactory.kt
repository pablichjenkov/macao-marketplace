package com.macaosoftware.sdui.app.domain

import com.macaosoftware.component.core.Component
import com.macaosoftware.component.core.NavItem
import kotlinx.serialization.json.JsonObject

interface MacaoComponentFactory {

    fun getNavItemOf(
        componentJson: JsonObject
    ): NavItem

    fun getComponentInstanceOf(
        componentJson: JsonObject
    ): Component
}