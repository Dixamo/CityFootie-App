package com.example.cityfootie_compose.usecases.register

import com.example.cityfootie_compose.datasource.PlayerRepository
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class PostPlayerUsecasesImpl @Inject constructor(
    private val playerRepository: PlayerRepository
) : PostPlayerUsecases{
    override suspend fun postPlayer(newPlayer: Player): Response<Void> {
        return playerRepository.postPlayer(newPlayer)
    }
}