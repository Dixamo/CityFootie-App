package com.example.cityfootie_compose.datasource.remote

import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FootieAPI {
    @GET("players")
    suspend fun getPlayer(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<Player>

    @POST("players")
    suspend fun postPlayer(
        //@Query("email") email: String,
        //@Query("password") password: String
    ): Response<Player>
}