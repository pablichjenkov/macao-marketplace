package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import com.macaosoftware.component.IosComponentRender
import com.macaosoftware.plugin.IosBridge
import com.macaosoftware.sdui.app.plugin.MacaoApplicationState

@Composable
fun IosMacaoApplication(
    iosBridge: IosBridge,
    onBackPress: () -> Unit,
    macaoApplicationState: MacaoApplicationState,
    splashScreenContent: @Composable () -> Unit
) {

    val rootComponent = macaoApplicationState.rootComponentState.value
    rootComponent.ifNotNull {
        IosComponentRender(
            rootComponent = it,
            iosBridge = iosBridge,
            onBackPress = onBackPress
        )
    }.elseIfNull {
        splashScreenContent()
        macaoApplicationState.fetchRootComponent()
    }
}
