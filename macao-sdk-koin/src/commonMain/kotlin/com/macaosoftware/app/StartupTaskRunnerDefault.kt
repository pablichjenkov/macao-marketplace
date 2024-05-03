package com.macaosoftware.app

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class StartupTaskRunnerDefault(
    val startupTasks: List<StartupTask>
) : StartupTaskRunner {

    override fun initialize(
        koinComponent: KoinComponent
    ): Flow<StartupTaskStatus> = flow {

        startupTasks.forEach { startupTask ->

            if (startupTask.shouldShowLoader()) {
                emit(StartupTaskStatus.Running(startupTask.name()))
            }

            startupTask.initialize(koinComponent)
        }

        emit(StartupTaskStatus.CompleteSuccess)
    }
}
