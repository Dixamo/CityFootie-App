package com.example.cityfootie_compose.usecases.put_match

import com.example.cityfootie_compose.datasource.FootieRepository
import com.example.cityfootie_compose.model.FootballMatch
import retrofit2.Response
import javax.inject.Inject

class PutFootballMatchUsecasesImpl @Inject constructor(
    private val footieRepository: FootieRepository
): PutFootballMatchUsecases{
    override suspend fun putFootballMatch(email: String, latitude: Double, longitude: Double): Response<Void> {
        return footieRepository.putFootballMatch(email, latitude, longitude)
    }
}