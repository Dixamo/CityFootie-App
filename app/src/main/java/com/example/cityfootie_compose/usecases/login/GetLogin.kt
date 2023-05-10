package com.example.cityfootie_compose.usecases.login

import com.example.cityfootie_compose.model.Player
import retrofit2.Response

interface GetLogin {
    suspend fun login(email: String, password: String): Response<Player>?
}