package com.example.cityfootie_compose.usecases.put_match

import com.example.cityfootie_compose.datasource.FootieRepository
import retrofit2.Response
import javax.inject.Inject

class PutFootballMatchUsecasesImpl @Inject constructor(
    private val footieRepository: FootieRepository
) : PutFootballMatchUsecases {
    override suspend fun putFootballMatch(
        latitude: Double,
        longitude: Double,
        email: String,
    ): Response<Void> {
        return footieRepository.putFootballMatch(latitude, longitude, email)
    }
}