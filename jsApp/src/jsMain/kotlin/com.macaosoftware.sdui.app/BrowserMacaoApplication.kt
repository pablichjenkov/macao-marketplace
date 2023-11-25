package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.macaosoftware.component.BrowserComponentRender
import com.macaosoftware.component.core.Component
import com.macaosoftware.plugin.JsBridge
import com.macaosoftware.sdui.app.plugin.MacaoApplicationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
