package com.macaosoftware.sdui.app

import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.sdui.app.plugin.AuthPluginJVM

actual fun Platform() : Boolean{
    return true
}
actual fun OSVersion(): String{
    return "JVM OS"
}
actual fun Plugin(): AuthPlugin {
    return AuthPluginJVM()
}