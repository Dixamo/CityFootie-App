package com.example.cityfootie_compose.datasource

import com.example.cityfootie_compose.datasource.remote.FootieRemoteDataSource
import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class FootieRepositoryImpl @Inject constructor(
    private val footieRemoteDataSource: FootieRemoteDataSource
) : FootieRepository {
    override suspend fun getPlayer(email: String, password: String): Response<Player> {
        return footieRemoteDataSource.getPlayer(email, password)
    }

    override suspend fun postPlayer(newPlayer: Player): Response<Void> {
        return footieRemoteDataSource.postPlayer(newPlayer)
    }

    override suspend fun updatePlayer(email: String, name: String, surnames: String, number: Int): Response<Void> {
        return footieRemoteDataSource.updatePlayer(email, name, surnames, number)
    }

    override suspend fun getFootballMatch(latitude: Double, longitude: Double): Response<FootballMatch> {
        return footieRemoteDataSource.getFootballMatch(latitude, longitude)
    }
}