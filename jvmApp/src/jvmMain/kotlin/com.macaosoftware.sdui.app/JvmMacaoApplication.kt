package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.WindowState
import com.macaosoftware.component.DesktopComponentRender
import com.macaosoftware.component.core.Component
import com.macaosoftware.platform.DesktopBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun JvmMacaoApplication(
    windowState: WindowState,
    desktopBridge: DesktopBridge,
    onBackPress: () -> Unit,
    rootComponentProvider: RootComponentProvider,
    splashScreenContent: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var rootComponent by remember(desktopBridge, rootComponentProvider) {
        mutableStateOf<Component?>(null)
    }
    rootComponent.ifNotNull {
        DesktopComponentRender(
            rootComponent = it,
            windowState = windowState,
            desktopBridge = desktopBridge,
            onBackPress = onBackPress
        )
    }.elseIfNull {
        splashScreenContent()
        coroutineScope.launch {
            rootComponent = withContext(Dispatchers.IO) {
                rootComponentProvider.provideRootComponent()
            }
        }
    }
}
