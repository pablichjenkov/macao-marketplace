package com.macaosoftware.app

import org.koin.core.module.Module

interface RootKoinModuleInitializer {
    suspend fun initialize() : Module
}