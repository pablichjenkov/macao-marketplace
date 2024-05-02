package com.macaosoftware.app

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StartupTaskRunnerDefault(
    val startupTasks: List<StartupTask>
) : StartupTaskRunner {

    override fun initialize(
        koinInjector: KoinInjector
    ): Flow<StartupTaskStatus> = flow {

        startupTasks.forEach { startupTask ->

            if (startupTask.shouldShowLoader()) {
                emit(StartupTaskStatus.Running(startupTask.name()))
            }

            startupTask.initialize(koinInjector)
        }

        emit(StartupTaskStatus.CompleteSuccess)
    }
}
