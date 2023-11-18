package com.macaosoftware.sdui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import com.macaosoftware.platform.JsBridge
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val jsBridge = JsBridge()
        val rootComponentProvider = BrowserRootComponentProvider(jsBridge)
        CanvasBasedWindow("Macao SDUI Demo") {
            BrowserMacaoApplication(
                jsBridge = jsBridge,
                onBackPress = {
                    println("Back press dispatched in root node")
                },
                rootComponentProvider = rootComponentProvider
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Example of Browser Splash Screen"
                    )
                }
            }
        }
    }
}
