package com.macaosoftware.sdui.app

import com.macaosoftware.plugin.AuthPlugin
import io.ktor.util.Platform

expect fun Platform() : Boolean
expect fun OSVersion(): String
expect fun Plugin(): AuthPlugin