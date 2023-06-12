package com.example.cityfootie_compose.usecases.get_players_by_match

import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface GetPlayersByFootballMatchUsecases {
    suspend fun getPlayersByFootballMatch(
        latitude: Double,
        longitude: Double
    ): Response<Set<Player>>
}