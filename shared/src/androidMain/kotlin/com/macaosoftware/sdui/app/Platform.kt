package com.macaosoftware.sdui.app

import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.sdui.plugin.AuthPluginAndroid

actual fun Platform() : Boolean{
    return true
}
actual fun OSVersion(): String{
    return "Android OS"
}
actual fun Plugin(): AuthPlugin{
    return AuthPluginAndroid()
}