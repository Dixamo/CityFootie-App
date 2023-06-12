package com.example.cityfootie_compose.usecases.get_players_by_match

import com.example.cityfootie_compose.datasource.FootieRepository
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class GetPlayersByFootballMatchUsecasesImpl @Inject constructor(
    private val footieRepository: FootieRepository
) : GetPlayersByFootballMatchUsecases {
    override suspend fun getPlayersByFootballMatch(
        latitude: Double,
        longitude: Double
    ): Response<Set<Player>> {
        return footieRepository.getPlayersByFootballMatch(latitude, longitude)
    }
}