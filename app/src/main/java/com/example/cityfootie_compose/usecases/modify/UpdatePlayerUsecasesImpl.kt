package com.example.cityfootie_compose.usecases.modify

import com.example.cityfootie_compose.datasource.FootieRepository
import retrofit2.Response
import javax.inject.Inject

class UpdatePlayerUsecasesImpl @Inject constructor(
    private val footieRepository: FootieRepository
) : UpdatePlayerUsecases {
    override suspend fun updatePlayer(
        email: String,
        name: String,
        surnames: String,
        username: String,
        number: Int
    ): Response<Void> {
        return footieRepository.updatePlayer(email, name, surnames, username, number)
    }
}