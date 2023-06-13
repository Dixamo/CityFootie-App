package com.example.cityfootie_compose.usecases.login

import com.example.cityfootie_compose.datasource.FootieRepository
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import javax.inject.Inject

class GetPlayerUsecasesImpl @Inject constructor(
    private val footieRepository: FootieRepository
) : GetPlayerUsecases {
    override suspend fun getPlayer(
        email: String,
        password: String
    ): Response<Player> {
        return footieRepository.getPlayer(email, password)
    }

    override suspend fun getPlayerByEmail(
        email: String
    ): Response<Player> {
        return footieRepository.getPlayerByEmail(email)
    }
}