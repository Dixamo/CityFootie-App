package com.example.cityfootie_compose.usecases.register

import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface PostPlayerUsecases {
    suspend fun postPlayer(newPlayer: Player): Response<Void>?
}