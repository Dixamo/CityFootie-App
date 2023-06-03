package com.example.cityfootie_compose.usecases.get_match

import com.example.cityfootie_compose.datasource.FootieRepository
import com.example.cityfootie_compose.model.FootballMatch
import retrofit2.Response
import javax.inject.Inject

class GetFootballMatchUsecasesImpl @Inject constructor(
    private val footieRepository: FootieRepository
): GetFootballMatchUsecases {
    override suspend fun getFootballMatch(latitude: Double, longitude: Double): Response<FootballMatch> {
        return footieRepository.getFootballMatch(latitude, longitude)
    }
}