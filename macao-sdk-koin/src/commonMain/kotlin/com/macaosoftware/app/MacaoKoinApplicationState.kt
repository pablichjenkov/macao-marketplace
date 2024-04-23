package com.macaosoftware.app

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.component.core.Component
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.dsl.koinApplication

class MacaoKoinApplicationState(
    dispatcher: CoroutineDispatcher,
    val rootComponentKoinProvider: RootComponentKoinProvider,
    private val koinRootModuleInitializer: KoinRootModuleInitializer
) {

    var stage = mutableStateOf<Stage>(Stage.Created)
    private val coroutineScope = CoroutineScope(dispatcher)

    fun start() {
        coroutineScope.launch {

            stage.value = Stage.Loading
            val appModule = koinRootModuleInitializer.initialize()

            val koinApplication = koinApplication {
                printLogger()
                modules(appModule)
            }

            val koinDiContainer = KoinDiContainer(koinApplication)

            val rootComponent = rootComponentKoinProvider.provideRootComponent(koinDiContainer)

            stage.value = Stage.Started(rootComponent)
        }
    }
}

sealed class Stage {
    data object Created : Stage()
    data object Loading : Stage()
    class Started(val rootComponent: Component) : Stage()
}
