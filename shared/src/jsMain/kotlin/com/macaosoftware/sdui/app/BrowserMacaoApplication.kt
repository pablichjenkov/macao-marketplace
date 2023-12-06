package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import com.macaosoftware.component.BrowserComponentRender
import com.macaosoftware.plugin.JsBridge
import com.macaosoftware.plugin.MacaoApplicationState
import com.macaosoftware.plugin.elseIfNull
import com.macaosoftware.plugin.ifNotNull

@Composable
fun BrowserMacaoApplication(
    jsBridge: JsBridge,
    onBackPress: () -> Unit,
    macaoApplicationState: MacaoApplicationState,
    splashScreenContent: @Composable () -> Unit
) {

    val rootComponent = macaoApplicationState.rootComponentState.value
    rootComponent.ifNotNull {
        BrowserComponentRender(
            rootComponent = it,
            jsBridge = jsBridge,
            onBackPress = onBackPress
        )
    }.elseIfNull {
        splashScreenContent()
        macaoApplicationState.fetchRootComponent()
    }
}
