package com.example.cityfootie_compose.usecases.modify

import com.example.cityfootie_compose.datasource.PlayerRepository
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class UpdatePlayerUsecasesImpl @Inject constructor(
    private val playerRepository: PlayerRepository
) : UpdatePlayerUsecases{
    override suspend fun updatePlayer(email: String, name: String, surnames: String, number: Int): Response<Void> {
        return playerRepository.updatePlayer(email, name, surnames, number)
    }
}