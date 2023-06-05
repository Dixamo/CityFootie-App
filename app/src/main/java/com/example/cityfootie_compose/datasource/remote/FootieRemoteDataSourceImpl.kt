package com.example.cityfootie_compose.datasource.remote

import com.example.cityfootie_compose.datasource.remote.FootieAPI
import com.example.cityfootie_compose.datasource.remote.FootieRemoteDataSource
import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.util.DispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class FootieRemoteDataSourceImpl @Inject constructor(
    private val footieAPI: FootieAPI,
    private val dispatcherProvider: DispatcherProvider
) : FootieRemoteDataSource {
    override suspend fun getPlayer(email: String, password: String): Response<Player> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.getPlayer(email, password)
        }

    override suspend fun postPlayer(newPlayer: Player): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.postPlayer(newPlayer)
        }

    override suspend fun updatePlayer(email: String, name: String, surnames: String, username: String, number: Int): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.updatePlayer(email, name, surnames, username, number)
        }
    override suspend fun getFootballMatch(latitude: Double, longitude: Double): Response<FootballMatch> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.getFootballMatch(latitude, longitude)
        }

    override suspend fun postFootballMatch(newFootballMatch: FootballMatch): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.postFootballMatch(newFootballMatch)
        }

    override suspend fun putFootballMatch(email: String, latitude: Double, longitude: Double): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.putFootballMatch(email, latitude, longitude)
        }

    override suspend fun getPlayerByEmail(email: String): Response<Player> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.getPlayerByEmail(email)
        }

}