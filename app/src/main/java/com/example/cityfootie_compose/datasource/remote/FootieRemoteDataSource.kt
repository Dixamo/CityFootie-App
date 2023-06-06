package com.example.cityfootie_compose.datasource.remote

import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface FootieRemoteDataSource {
    suspend fun getPlayer(email: String, password: String): Response<Player>
    suspend fun postPlayer(newPlayer: Player): Response<Void>
    suspend fun updatePlayer(email: String, name: String, surnames: String, username: String, number: Int): Response<Void>
    suspend fun getFootballMatch(latitude: Double, longitude: Double): Response<FootballMatch>
    suspend fun postFootballMatch(newFootballMatch: FootballMatch): Response<Void>
    suspend fun putFootballMatch(latitude: Double, longitude: Double, email: String): Response<Void>
    suspend fun getPlayerByEmail(email: String): Response<Player>
    suspend fun getPlayersByFootballMatch(latitude: Double, longitude: Double): Response<Set<Player>>
}