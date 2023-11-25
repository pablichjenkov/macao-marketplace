package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import com.macaosoftware.component.AndroidComponentRender
import com.macaosoftware.sdui.app.plugin.MacaoApplicationState

@Composable
fun AndroidMacaoApplication(
    onBackPress: () -> Unit,
    macaoApplicationState: MacaoApplicationState,
    splashScreenContent: @Composable () -> Unit
) {

    val rootComponent = macaoApplicationState.rootComponentState.value
    rootComponent.ifNotNull {
        AndroidComponentRender(
            rootComponent = it,
            onBackPress = onBackPress
        )
    }.elseIfNull {
        splashScreenContent()
        macaoApplicationState.fetchRootComponent()
    }
}
