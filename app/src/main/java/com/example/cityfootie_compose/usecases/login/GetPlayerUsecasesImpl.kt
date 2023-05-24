package com.example.cityfootie_compose.usecases.login

import com.example.cityfootie_compose.datasource.PlayerRepository
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class GetPlayerUsecasesImpl @Inject constructor(
    private val playerRepository: PlayerRepository
) : GetPlayerUsecases {
    override suspend fun getPlayer(email: String, password: String): Response<Player> {
        return playerRepository.getPlayer(email, password)
    }
}