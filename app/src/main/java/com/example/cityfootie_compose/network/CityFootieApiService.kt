package com.example.cityfootie_compose.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = ""

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface CityFootieApiService {
    @GET("players")
    suspend fun login(): String
}

object CityFootieApi {
    val retrofitService : CityFootieApiService by lazy {
        retrofit.create(CityFootieApiService::class.java)
    }
}