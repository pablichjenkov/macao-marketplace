package com.pablichj.incubator.amadeus.storage

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.pablichj.incubator.amadeus.Database

actual class DriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:amadeus_api.db")
        Database.Schema.create(driver).await()
        return driver
    }
}