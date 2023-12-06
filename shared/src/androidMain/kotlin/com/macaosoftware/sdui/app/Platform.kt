package com.macaosoftware.sdui.app

actual fun Platform() : Boolean{
    return true
}
actual fun OSVersion(): String{
    return "Android OS"
}