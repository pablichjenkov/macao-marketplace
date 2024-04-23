package com.macaosoftware.sdui.app

import androidx.compose.ui.window.CanvasBasedWindow
import com.macaosoftware.app.MacaoKoinApplication
import com.macaosoftware.app.MacaoKoinApplicationState
import kotlinx.coroutines.Dispatchers
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        val rootComponentProvider = BrowserRootComponentProvider()
        val applicationState = MacaoKoinApplicationState(
            dispatcher = Dispatchers.Default,
            rootComponentKoinProvider = rootComponentProvider,
            koinRootModuleInitializer = BrowserKoinModuleInitializer()
        )

        CanvasBasedWindow("Macao SDUI Demo") {
            MacaoKoinApplication(applicationState = applicationState)
        }
    }
}
