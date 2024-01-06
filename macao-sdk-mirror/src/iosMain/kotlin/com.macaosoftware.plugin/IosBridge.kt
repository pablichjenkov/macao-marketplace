package com.macaosoftware.plugin

@ObjCName(name = "IosBridge", exact = true)
class IosBridge(
    val platformLifecyclePlugin: PlatformLifecyclePlugin,
    val authPlugin: AuthPlugin
)
