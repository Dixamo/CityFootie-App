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

    override suspend fun postPlayer(newPlayer: Player): Response<Void> {
        return playerRemoteDataSource.postPlayer(newPlayer)
    }

    override suspend fun updatePlayer(email: String, name: String, surnames: String, number: Int): Response<Void> {
        return playerRemoteDataSource.updatePlayer(email, name, surnames, number)
    }
}