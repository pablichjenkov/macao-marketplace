package com.pablichj.incubator.amadeus.storage

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import com.pablichj.incubator.amadeus.Database
import org.w3c.dom.Worker

actual class DriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return WebWorkerDriver(
            Worker(
                js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
            )
        ).also {
            Database.Schema.awaitCreate(it)
        }
    }
}
