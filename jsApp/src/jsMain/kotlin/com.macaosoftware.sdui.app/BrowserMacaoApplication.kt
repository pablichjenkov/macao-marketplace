package com.macaosoftware.sdui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.macaosoftware.component.BrowserComponentRender
import com.macaosoftware.component.core.Component
import com.macaosoftware.platform.JsBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun BrowserMacaoApplication(
    jsBridge: JsBridge,
    onBackPress: () -> Unit,
    rootComponentProvider: RootComponentProvider,
    splashScreenContent: @Composable () -> Unit
) {
    var rootComponent by remember(jsBridge, rootComponentProvider) {
        mutableStateOf<Component?>(null)
    }
    rootComponent.ifNotNull {
        BrowserComponentRender(
            rootComponent = it,
            jsBridge = jsBridge,
            onBackPress = onBackPress
        )
    }.elseIfNull {
        splashScreenContent()
        rememberCoroutineScope().launch {
            rootComponent = withContext(Dispatchers.Default) {
                rootComponentProvider.provideRootComponent()
            }
        }
    }
}
