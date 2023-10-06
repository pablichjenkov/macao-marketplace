package com.pablichj.incubator.amadeus.storage

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.pablichj.incubator.amadeus.Database

expect class DriverFactory {
  suspend fun createDriver(
    //schema: SqlSchema<QueryResult.AsyncValue<Unit>>
  ): SqlDriver
}

suspend fun createDatabase(driverFactory: DriverFactory): Database {
  val driver = driverFactory.createDriver()
  return Database(driver)
}
