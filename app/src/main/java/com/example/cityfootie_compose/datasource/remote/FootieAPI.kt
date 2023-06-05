package com.example.cityfootie_compose.datasource.remote

import com.example.cityfootie_compose.model.FootballMatch
import com.example.cityfootie_compose.model.Player
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FootieAPI {
    @GET("players")
    suspend fun getPlayer(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<Player>

    @GET("players/{email}")
    suspend fun getPlayerByEmail(
        @Path("email") email: String
    ): Response<Player>

    @POST("players")
    suspend fun postPlayer(
        @Body newPlayer: Player
    ): Response<Void>

    @PUT("players/{email}")
    suspend fun updatePlayer(
        //@Body newPlayer: Player,
        @Path("email") email: String,
        @Query("name") name: String,
        @Query("surnames") surnames: String,
        @Query("number") number: Int
    ) :Response<Void>

    @GET("footballMatches")
    suspend fun getFootballMatch(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<FootballMatch>

    @POST("footballMatches")
    suspend fun postFootballMatch(
        @Body newFootballMatch: FootballMatch
    ): Response<Void>

    @PUT("footballMatches")
    suspend fun putFootballMatch(
        @Query("email") email: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<Void>
}