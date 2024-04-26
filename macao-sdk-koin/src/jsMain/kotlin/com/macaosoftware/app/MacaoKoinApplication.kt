package com.macaosoftware.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.macaosoftware.component.BrowserComponentRender

@Composable
fun MacaoKoinApplication(
    applicationState: MacaoKoinApplicationState
) {

    when (val stage = applicationState.stage.value) {

        Stage.Created -> {
            SideEffect { applicationState.start() }
        }

        Stage.InitializingDiAndRootComponent -> {
        }

        is Stage.Started -> {
            BrowserComponentRender(rootComponent = stage.rootComponent)
        }
    }
}
