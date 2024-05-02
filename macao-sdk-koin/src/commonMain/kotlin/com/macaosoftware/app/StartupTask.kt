package com.macaosoftware.app

import com.macaosoftware.util.MacaoResult

interface StartupTask {

    fun name(): String

    /**
     * This function dictates whether the initialization will actually take place
     * or it will rely on the database cached values.
     * */
    fun shouldShowLoader(): Boolean

    /**
     * This function should be executed in io/default dispatcher.
     * Things like Database Migration and LaunchDarkly initialization
     * are examples of StartupTasks.
     * */
    suspend fun initialize(koinInjector: KoinInjector): MacaoResult<Unit>
}
