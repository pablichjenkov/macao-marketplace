package com.pablichj.incubator.amadeus.common

import kotlinx.coroutines.flow.Flow

interface SingleUseCase<out R> {
    suspend fun doWork(): R
}

interface FlowUseCase<out R> {
    suspend fun doWork(): Flow<R>
}
