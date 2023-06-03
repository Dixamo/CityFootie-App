package com.example.cityfootie_compose.usecases.get_match

import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface GetFootballMatchUsecases {
    suspend fun getFootballMatch(latitude: Double, longitude: Double): Response<FootballMatch>?
}