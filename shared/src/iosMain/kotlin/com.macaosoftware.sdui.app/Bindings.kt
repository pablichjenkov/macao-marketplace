package com.macaosoftware.sdui.app

import androidx.compose.ui.window.ComposeUIViewController
import com.macaosoftware.app.IosMacaoApplication
import com.macaosoftware.app.MacaoApplicationState
import com.macaosoftware.plugin.DefaultPlatformLifecyclePlugin
import com.macaosoftware.plugin.FirebaseAuth
import com.macaosoftware.plugin.IosBridge
import com.macaosoftware.sdui.app.view.SplashScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.UIKit.UIViewController

fun buildDemoMacaoApplication(
    iosBridge: IosBridge,
    firebaseAuth: FirebaseAuth,
    onBackPress: () -> Unit = {}
): UIViewController = ComposeUIViewController {

    IosMacaoApplication(
        iosBridge = iosBridge,
        onBackPress = onBackPress,
        macaoApplicationState = MacaoApplicationState(
            Dispatchers.IO,
            IosRootComponentProvider(iosBridge, firebaseAuth)
        ),
        splashScreenContent = { SplashScreen() }
    )
}

// Todo: Replace with swift implementation
fun createPlatformBridge(): IosBridge {
    return IosBridge(
        platformLifecyclePlugin = DefaultPlatformLifecyclePlugin()
    )
}
