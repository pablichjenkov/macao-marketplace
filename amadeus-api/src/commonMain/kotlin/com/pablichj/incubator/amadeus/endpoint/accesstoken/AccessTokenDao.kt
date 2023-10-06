package com.pablichj.incubator.amadeus.endpoint.accesstoken

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.pablichj.incubator.amadeus.Database
import com.pablichj.incubator.amadeus.common.ITimeProvider
import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.AccessToken
import com.pablichj.incubator.amadeus.endpoint.accesstoken.model.toModel

class AccessTokenDaoDelight(
    private val database: Database,
    private val timeProvider: ITimeProvider
) : IAccessTokenDao {

    override suspend fun insert(accessToken: AccessToken) {
        database.accessTokenTbQueries.insert(
            accessToken.toTable(timeProvider.epochSeconds().inWholeSeconds)
        )
    }

    override suspend fun lastOrNull(): AccessToken? {
        return database.accessTokenTbQueries.selectLast().awaitAsOneOrNull()?.toModel()
    }

    override suspend fun all(): List<AccessToken> {
        return database.accessTokenTbQueries.selectAll().awaitAsList().map { it.toModel() }
    }

}

interface IAccessTokenDao {
    suspend fun insert(accessToken: AccessToken)
    suspend fun lastOrNull(): AccessToken?
    suspend fun all(): List<AccessToken>
}