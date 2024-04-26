package com.macaosoftware.app

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.component.core.Component
import com.macaosoftware.plugin.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.dsl.koinApplication

class MacaoKoinApplicationState(
    val rootComponentKoinProvider: RootComponentKoinProvider,
    private val rootModuleKoinInitializer: RootModuleKoinInitializer,
    private val dispatchers: CoroutineDispatchers = CoroutineDispatchers.Default,
) {

    var stage = mutableStateOf<Stage>(Stage.Created)
    private val coroutineScope = CoroutineScope(dispatchers.main)

    fun start() {
        coroutineScope.launch {

            stage.value = Stage.InitializingDiAndRootComponent("Initializing: DiContainer")

            val koinApplication = withContext(dispatchers.default) {

                val appModule = rootModuleKoinInitializer.initialize()
                // val commonModule = initCommonModule()
                koinApplication {
                    printLogger()
                    modules(appModule)
                }
            }

            stage.value = Stage.InitializingDiAndRootComponent("Initializing: RootComponent")

            val rootComponent = withContext(dispatchers.default) {
                val diContainerKoin = DiContainerKoin(koinApplication)
                rootComponentKoinProvider.provideRootComponent(diContainerKoin)
            }

            stage.value = Stage.Started(rootComponent)
        }
    }
}

sealed class Stage {
    data object Created : Stage()
    data class InitializingDiAndRootComponent(val initializerName: String) : Stage()
    data class Started(val rootComponent: Component) : Stage()
}
