package com.example.cityfootie_compose.datasource

import com.example.cityfootie_compose.datasource.remote.PlayerRemoteDataSource
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val playerRemoteDataSource: PlayerRemoteDataSource
) : PlayerRepository {
    override suspend fun getPlayer(email: String, password: String): Response<Player> {
        return playerRemoteDataSource.getPlayer(email, password)
    }
}