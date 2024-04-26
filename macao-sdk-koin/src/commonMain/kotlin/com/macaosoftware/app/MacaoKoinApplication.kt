package com.macaosoftware.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.macaosoftware.component.PlatformComponentRenderer

@Composable
fun MacaoKoinApplication(applicationState: MacaoKoinApplicationState) {

    when (val stage = applicationState.stage.value) {

        Stage.Created -> {
            SideEffect {
                applicationState.start()
            }
        }

        is Stage.InitializingDiAndRootComponent -> {
            Box(Modifier.fillMaxSize().background(Color.LightGray)) {
                BasicText(
                    modifier = Modifier.wrapContentSize().align(Alignment.Center),
                    text = stage.initializerName
                )
            }
        }

        is Stage.Started -> {
            PlatformComponentRenderer(rootComponent = stage.rootComponent)
        }
    }
}
