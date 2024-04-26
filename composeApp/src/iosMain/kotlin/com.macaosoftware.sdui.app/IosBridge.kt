package com.macaosoftware.sdui.app

import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.AccountPluginEmpty

@ObjCName(name = "IosBridge", exact = true)
class IosBridge(
    val accountPlugin: AccountPlugin = AccountPluginEmpty()
)
