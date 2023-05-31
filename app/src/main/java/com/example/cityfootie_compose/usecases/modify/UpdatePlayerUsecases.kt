package com.example.cityfootie_compose.usecases.modify

import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface UpdatePlayerUsecases {
    suspend fun updatePlayer(email: String, name: String, surnames: String, number: Int): Response<Void>?
}