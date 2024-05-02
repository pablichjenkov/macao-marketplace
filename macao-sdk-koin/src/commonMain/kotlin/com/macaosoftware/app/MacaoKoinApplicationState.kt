package com.macaosoftware.app

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.component.core.Component
import com.macaosoftware.plugin.CoroutineDispatchers
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.dsl.koinApplication

class MacaoKoinApplicationState(
    private val rootKoinModuleInitializer: RootKoinModuleInitializer,
    private val startupTaskRunner: StartupTaskRunner,
    private val rootComponentInitializer: RootComponentInitializer,
    private val dispatchers: CoroutineDispatchers = CoroutineDispatchers.Default
) {

    var stage = mutableStateOf<Stage>(Created)
    private val coroutineScope = CoroutineScope(dispatchers.main)

    fun initialize() = coroutineScope.launch {

        val koinApplication = withContext(dispatchers.default) {

            val rootModule = rootKoinModuleInitializer.initialize()
            // val commonModule = initCommonModule()
            koinApplication {
                printLogger()
                modules(rootModule)
            }
        }

        runStartupTasks(KoinInjector(koinApplication))
    }

    private suspend fun runStartupTasks(koinInjector: KoinInjector) {

        startupTaskRunner
            .initialize(koinInjector)
            .flowOn(dispatchers.default)
            .collect { status ->
                when (status) {
                    is StartupTaskStatus.Running -> {
                        stage.value = Initializing.StartupTask(status.taskName)
                    }

                    is StartupTaskStatus.CompleteError -> {
                        stage.value = InitializationError(status.errorMsg)
                    }

                    is StartupTaskStatus.CompleteSuccess -> {
                        initializeRootComponent(koinInjector)
                    }
                }
            }
    }

    private suspend fun initializeRootComponent(koinInjector: KoinInjector) {

        if (rootComponentInitializer.shouldShowLoader()) {
            stage.value = Initializing.RootComponent
        }
        val result = withContext(dispatchers.default) {
            rootComponentInitializer.initialize(koinInjector)
        }

        when(result) {
            is MacaoResult.Error -> {
                stage.value = InitializationError(result.error.toString())
            }
            is MacaoResult.Success -> {
                stage.value = InitializationSuccess(result.value)
            }
        }
    }

}

sealed class Stage
data object Created : Stage()

sealed class Initializing : Stage() {
    // data object KoinRootModule : Initializing()
    data class StartupTask(val taskName: String) : Initializing()
    data object RootComponent : Initializing()
}

class InitializationError(val errorMsg: String) : Stage()
class InitializationSuccess(val rootComponent: Component) : Stage()
