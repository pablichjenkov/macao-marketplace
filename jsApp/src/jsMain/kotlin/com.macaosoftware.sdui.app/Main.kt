package com.macaosoftware.sdui.app

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import com.macaosoftware.plugin.JsBridge
import com.macaosoftware.sdui.app.view.SplashScreen
import kotlinx.coroutines.Dispatchers
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val jsBridge = JsBridge()
        val rootComponentProvider = BrowserRootComponentProvider(jsBridge)
        val applicationState = MacaoKoinApplicationState(
            dispatcher = Dispatchers.Default,
            rootComponentKoinProvider = rootComponentProvider,
            koinModuleInitializer = BrowserKoinModuleInitializer()
        )

        CanvasBasedWindow("Macao SDUI Demo") {
            MacaoKoinApplication(
                jsBridge = jsBridge,
                onBackPress = {
                    println("Back press dispatched in root node")
                },
                applicationState = applicationState,
                splashScreenContent = { SplashScreen() }
            )
        }
    }
}
