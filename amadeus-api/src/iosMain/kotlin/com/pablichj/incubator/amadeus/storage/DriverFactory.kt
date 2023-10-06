package com.pablichj.incubator.amadeus.storage

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.pablichj.incubator.amadeus.Database

actual class DriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            Database.Schema.synchronous(),
            "amadeus_demo.db"
        )
    }
}