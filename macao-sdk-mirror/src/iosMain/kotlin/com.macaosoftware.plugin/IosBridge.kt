package com.macaosoftware.plugin

import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(name = "IosBridge", exact = true)
class IosBridge(
    val platformLifecyclePlugin: PlatformLifecyclePlugin,
    val authPlugin: AuthPlugin
)