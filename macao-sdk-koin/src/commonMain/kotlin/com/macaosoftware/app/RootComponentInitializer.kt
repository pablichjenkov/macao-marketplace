package com.macaosoftware.app

import com.macaosoftware.component.core.Component
import com.macaosoftware.util.MacaoResult
import org.koin.core.component.KoinComponent
import kotlin.native.ObjCName

@ObjCName("RootComponentProvider")
interface RootComponentInitializer {
    fun shouldShowLoader(): Boolean
    suspend fun initialize(koinComponent: KoinComponent): MacaoResult<Component>
}
