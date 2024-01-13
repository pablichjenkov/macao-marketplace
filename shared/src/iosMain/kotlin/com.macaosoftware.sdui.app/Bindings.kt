package com.macaosoftware.sdui.app

import androidx.compose.ui.window.ComposeUIViewController
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.plugin.DefaultPlatformLifecyclePlugin
import com.macaosoftware.plugin.IosBridge
import com.macaosoftware.plugin.account.FirebaseAccountPlugin
import com.macaosoftware.plugin.account.FirebaseAuthKmpWrapper
import com.macaosoftware.sdui.app.view.SplashScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.UIKit.UIViewController

fun buildDemoMacaoApplication(
    iosBridge: IosBridge,
    onBackPress: () -> Unit = {}
): UIViewController = ComposeUIViewController {

    MacaoKoinApplication(
        iosBridge = iosBridge,
        onBackPress = onBackPress,
        applicationState = MacaoKoinApplicationState(
            dispatcher = Dispatchers.IO,
            rootComponentKoinProvider = IosRootComponentProvider(),
            koinModuleInitializer = IosKoinModuleInitializer(iosBridge)
        ),
        splashScreenContent = { SplashScreen() }
    )
}

fun createPlatformBridge(
    firebaseAuthKmpWrapper: FirebaseAuthKmpWrapper
): IosBridge {

    // val accountPlugin = SupabaseAccountPlugin()
    val accountPlugin = FirebaseAccountPlugin(firebaseAuthKmpWrapper)

    return IosBridge(
        platformLifecyclePlugin = DefaultPlatformLifecyclePlugin(),
        accountPlugin = accountPlugin
    )
}
