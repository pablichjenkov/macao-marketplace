package com.macaosoftware.sdui.app

import androidx.compose.ui.window.ComposeUIViewController
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.plugin.account.FirebaseAccountPlugin
import com.macaosoftware.plugin.account.FirebaseAuthKmpWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.UIKit.UIViewController

fun buildDemoMacaoApplication(
    iosBridge: IosBridge,
    onBackPress: () -> Unit = {}
): UIViewController = ComposeUIViewController {

    MacaoKoinApplication(
        onBackPress = onBackPress,
        applicationState = MacaoKoinApplicationState(
            dispatcher = Dispatchers.IO,
            rootComponentKoinProvider = IosRootComponentProvider(),
            koinRootModuleInitializer = IosKoinModuleInitializer(iosBridge)
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
