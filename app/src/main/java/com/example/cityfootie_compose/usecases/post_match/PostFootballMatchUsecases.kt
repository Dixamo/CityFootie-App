package com.example.cityfootie_compose.usecases.post_match

import com.example.cityfootie_compose.model.FootballMatch
import retrofit2.Response

interface PostFootballMatchUsecases {
    suspend fun postFootballMatch(
        newFootballMatch: FootballMatch
    ): Response<Void>?
}