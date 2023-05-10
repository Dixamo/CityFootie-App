package com.example.cityfootie_compose.usecases.login

import com.example.cityfootie_compose.datasource.PlayerRepository
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class GetLoginImpl @Inject constructor(
    private val playerRepository: PlayerRepository
): GetLogin {
    override suspend fun login(email: String, password: String): Response<Player> {
        return playerRepository.login(email, password)
    }
}