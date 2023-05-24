package com.example.cityfootie_compose.datasource

import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface PlayerRepository {
    suspend fun getPlayer(email: String, password: String): Response<Player>
    suspend fun postPlayer(newPlayer: Player): Response<Void>
}