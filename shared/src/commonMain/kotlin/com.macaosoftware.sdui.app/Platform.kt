package com.macaosoftware.sdui.app

import io.ktor.util.Platform

expect fun Platform() : Boolean
expect fun OSVersion(): String