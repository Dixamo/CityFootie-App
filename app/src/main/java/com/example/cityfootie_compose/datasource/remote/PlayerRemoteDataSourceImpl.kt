package com.example.cityfootie_compose.datasource.remote

import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.util.DispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PlayerRemoteDataSourceImpl @Inject constructor(
    private val footieAPI: FootieAPI,
    private val dispatcherProvider: DispatcherProvider
) : PlayerRemoteDataSource {
    override suspend fun getPlayer(email: String, password: String): Response<Player> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.getPlayer(email, password)
        }

    override suspend fun postPlayer(newPlayer: Player): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.postPlayer(newPlayer)
        }

    override suspend fun updatePlayer(email: String, name: String, surnames: String, number: Int): Response<Void> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.updatePlayer(email, name, surnames, number)
        }
}