package com.example.cityfootie_compose.usecases.post_match

import com.example.cityfootie_compose.datasource.FootieRepository
import com.example.cityfootie_compose.model.FootballMatch
import retrofit2.Response
import javax.inject.Inject

class PostFootballMatchUsecasesImpl @Inject constructor(
    private val footieRepository: FootieRepository
) : PostFootballMatchUsecases{
    override suspend fun postFootballMatch(newFootballMatch: FootballMatch): Response<Void> {
        return footieRepository.postFootballMatch(newFootballMatch)
    }
}