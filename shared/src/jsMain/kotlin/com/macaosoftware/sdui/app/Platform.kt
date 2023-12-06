package com.macaosoftware.sdui.app

import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.sdui.app.plugin.AuthPluginJsMain

actual fun Platform() : Boolean{
    return true
}
actual fun OSVersion(): String{
    return "JS OS"
}
actual fun Plugin(): AuthPlugin {
    return AuthPluginJsMain()
}