package com.example.cityfootie_compose.usecases.post_player

import com.example.cityfootie_compose.datasource.FootieRepository
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class PostPlayerUsecasesImpl @Inject constructor(
    private val footieRepository: FootieRepository
) : PostPlayerUsecases {
    override suspend fun postPlayer(
        newPlayer: Player
    ): Response<Void> {
        return footieRepository.postPlayer(newPlayer)
    }
}