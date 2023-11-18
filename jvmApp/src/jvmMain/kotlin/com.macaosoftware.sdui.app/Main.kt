package com.macaosoftware.sdui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.macaosoftware.platform.DesktopBridge
import kotlin.system.exitProcess

fun main() {

    val desktopBridge = DesktopBridge()
    val windowState = WindowState(size = DpSize(800.dp, 600.dp))
    var rootComponentProvider by mutableStateOf(JvmRootComponentProvider())

    singleWindowApplication(
        title = "Macao SDUI Demo",
        state = windowState,
        undecorated = true
    ) {
        WindowWithCustomTopDecoration(
            onMinimizeClick = { windowState.isMinimized = true },
            onMaximizeClick = { windowState.isMinimized = false },
            onCloseClick = { exitProcess(0) },
            onRefreshClick = {
                rootComponentProvider = JvmRootComponentProvider()
            },
            onBackClick = {
                desktopBridge.backPressDispatcher.dispatchBackPressed()
            }
        ) {
            JvmMacaoApplication(
                windowState = windowState,
                desktopBridge = desktopBridge,
                onBackPress = { exitProcess(0) },
                rootComponentProvider = rootComponentProvider
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Example of JVM Splash Screen"
                    )
                }
            }
        }
    }
}
