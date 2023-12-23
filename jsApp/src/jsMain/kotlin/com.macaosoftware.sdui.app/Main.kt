package com.macaosoftware.sdui.app

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.macaosoftware.app.BrowserMacaoApplication
import com.macaosoftware.app.MacaoApplicationState
import com.macaosoftware.plugin.JsBridge
import com.macaosoftware.sdui.app.view.SplashScreen
import kotlinx.coroutines.Dispatchers
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val jsBridge = JsBridge()
        val rootComponentProvider = BrowserRootComponentProvider(jsBridge)
        val macaoApplicationState = MacaoApplicationState(
            Dispatchers.Default,
            rootComponentProvider
        )

        CanvasBasedWindow("Macao SDUI Demo") {
            BrowserMacaoApplication(
                jsBridge = jsBridge,
                onBackPress = {
                    println("Back press dispatched in root node")
                },
                macaoApplicationState = macaoApplicationState,
                splashScreenContent = { SplashScreen() }
            )
        }
    }
}
