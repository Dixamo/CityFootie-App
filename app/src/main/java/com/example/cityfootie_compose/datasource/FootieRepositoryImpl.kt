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

    override suspend fun updatePlayer(
        email: String,
        name: String,
        surnames: String,
        username: String,
        number: Int
    ): Response<Void> {
        return footieRemoteDataSource.updatePlayer(email, name, surnames, username, number)
    }

    override suspend fun getFootballMatch(
        latitude: Double,
        longitude: Double
    ): Response<FootballMatch> {
        return footieRemoteDataSource.getFootballMatch(latitude, longitude)
    }

    override suspend fun postFootballMatch(newFootballMatch: FootballMatch): Response<Void> {
        return footieRemoteDataSource.postFootballMatch(newFootballMatch)
    }

    override suspend fun putFootballMatch(
        latitude: Double,
        longitude: Double,
        email: String
    ): Response<Void> {
        return footieRemoteDataSource.putFootballMatch(latitude, longitude, email)
    }

    override suspend fun getPlayerByEmail(email: String): Response<Player> {
        return footieRemoteDataSource.getPlayerByEmail(email)
    }

    override suspend fun getPlayersByFootballMatch(
        latitude: Double,
        longitude: Double
    ): Response<Set<Player>> {
        return footieRemoteDataSource.getPlayersByFootballMatch(latitude, longitude)
    }
}