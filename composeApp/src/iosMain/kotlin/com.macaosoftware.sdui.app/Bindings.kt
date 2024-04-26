package com.macaosoftware.sdui.app

import androidx.compose.ui.window.ComposeUIViewController
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.plugin.account.FirebaseAccountPlugin
import com.macaosoftware.plugin.account.FirebaseAuthKmpWrapper
import platform.UIKit.UIViewController

fun buildDemoMacaoApplication(
    iosBridge: IosBridge
): UIViewController = ComposeUIViewController {

    MacaoKoinApplication(
        applicationState = MacaoKoinApplicationState(
            rootComponentKoinProvider = IosRootComponentProvider(),
            rootModuleKoinInitializer = IosKoinModuleInitializer(iosBridge)
        )
    )
}

fun createPlatformBridge(
    firebaseAuthKmpWrapper: FirebaseAuthKmpWrapper
): IosBridge {

    // val accountPlugin = SupabaseAccountPlugin()
    val accountPlugin = FirebaseAccountPlugin(firebaseAuthKmpWrapper)

    return IosBridge(
        accountPlugin = accountPlugin
    )
}
