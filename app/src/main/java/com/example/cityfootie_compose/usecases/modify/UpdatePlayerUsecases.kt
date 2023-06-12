package com.example.cityfootie_compose.usecases.modify

import retrofit2.Response

interface UpdatePlayerUsecases {
    suspend fun updatePlayer(
        email: String,
        name: String,
        surnames: String,
        username: String,
        number: Int
    ): Response<Void>?
}