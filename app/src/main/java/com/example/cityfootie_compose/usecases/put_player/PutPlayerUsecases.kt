package com.example.cityfootie_compose.usecases.put_player

import retrofit2.Response

interface PutPlayerUsecases {
    suspend fun updatePlayer(
        email: String,
        name: String,
        surnames: String,
        username: String,
        number: Int
    ): Response<Void>?
}