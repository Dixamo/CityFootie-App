package com.example.cityfootie_compose.usecases.post_player

import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface PostPlayerUsecases {
    suspend fun postPlayer(
        newPlayer: Player
    ): Response<Void>?
}