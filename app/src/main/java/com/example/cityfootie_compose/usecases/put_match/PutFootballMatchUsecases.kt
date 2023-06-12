package com.example.cityfootie_compose.usecases.put_match

import retrofit2.Response

interface PutFootballMatchUsecases {
    suspend fun putFootballMatch(
        latitude: Double,
        longitude: Double,
        email: String
    ): Response<Void>?
}