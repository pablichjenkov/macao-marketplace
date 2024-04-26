package com.macaosoftware.app

import org.koin.core.module.Module

interface RootModuleKoinInitializer {
    suspend fun initialize() : Module
}