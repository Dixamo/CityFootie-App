package com.example.cityfootie_compose.usecases.get_player

import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface GetPlayerUsecases {
    suspend fun getPlayer(
        email: String, password: String
    ): Response<Player>?

    suspend fun getPlayerByEmail(
        email: String
    ): Response<Player>?
}