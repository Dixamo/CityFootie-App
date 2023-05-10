package com.example.cityfootie_compose.datasource.remote

import com.example.cityfootie_compose.model.Player
import com.example.cityfootie_compose.util.DispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class PlayerRemoteDataSourceImpl @Inject constructor(
    private val footieAPI: FootieAPI,
    private val dispatcherProvider: DispatcherProvider
): PlayerRemoteDataSource {
    override suspend fun login(email: String, password: String): Response<Player> =
        withContext(dispatcherProvider.ioDispatcher) {
            return@withContext footieAPI.login(email, password)
        }
}