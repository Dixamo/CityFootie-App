package com.example.cityfootie_compose.di

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.cityfootie_compose.datasource.FootieRepository
import com.example.cityfootie_compose.datasource.FootieRepositoryImpl
import com.example.cityfootie_compose.datasource.remote.FootieAPI
import com.example.cityfootie_compose.datasource.remote.FootieRemoteDataSource
import com.example.cityfootie_compose.datasource.remote.FootieRemoteDataSourceImpl
import com.example.cityfootie_compose.usecases.get_match.GetFootballMatchUsecases
import com.example.cityfootie_compose.usecases.get_match.GetFootballMatchUsecasesImpl
import com.example.cityfootie_compose.usecases.get_players_by_match.GetPlayersByFootballMatchUsecases
import com.example.cityfootie_compose.usecases.get_players_by_match.GetPlayersByFootballMatchUsecasesImpl
import com.example.cityfootie_compose.usecases.get_player.GetPlayerUsecases
import com.example.cityfootie_compose.usecases.get_player.GetPlayerUsecasesImpl
import com.example.cityfootie_compose.usecases.put_player.PutPlayerUsecases
import com.example.cityfootie_compose.usecases.put_player.PutPlayerUsecasesImpl
import com.example.cityfootie_compose.usecases.post_match.PostFootballMatchUsecases
import com.example.cityfootie_compose.usecases.post_match.PostFootballMatchUsecasesImpl
import com.example.cityfootie_compose.usecases.put_match.PutFootballMatchUsecases
import com.example.cityfootie_compose.usecases.put_match.PutFootballMatchUsecasesImpl
import com.example.cityfootie_compose.usecases.post_player.PostPlayerUsecases
import com.example.cityfootie_compose.usecases.post_player.PostPlayerUsecasesImpl
import com.example.cityfootie_compose.util.DispatcherProvider
import com.example.cityfootie_compose.util.DispatcherProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            Handler(Looper.getMainLooper()).post {
                Log.d("SIGNALCALL ", request.toString())
            }
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor {}.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @SuppressLint("SimpleDateFormat")
    @Provides
    fun provideGson(): Gson {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+02:00")
        dateFormat.timeZone = TimeZone.getTimeZone("Europe/Madrid")
        return GsonBuilder().serializeNulls().setLenient().setDateFormat(dateFormat.toPattern()).create()
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder.protocols(mutableListOf(Protocol.HTTP_1_1)).build()
    }

    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient, gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080") // Emulador AndoridStudio 10.0.2.2 // JuanDa 192.168.1.63 / 192.168.88.19 // Miguel 192.168.1.132 // Sebas 192.168.1.11
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
    }

    @Provides
    @Singleton
    fun provideFootieService(
        retrofit: Retrofit
    ): FootieAPI {
        return retrofit.create(FootieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider = DispatcherProviderImpl()

    @Provides
    @Singleton
    fun provideFootieRemoteDataSource(
        footieAPI: FootieAPI, dispatcherProvider: DispatcherProvider
    ): FootieRemoteDataSource {
        return FootieRemoteDataSourceImpl(footieAPI, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideRepository(
        footieRemoteDataSource: FootieRemoteDataSource
    ): FootieRepository {
        return FootieRepositoryImpl(footieRemoteDataSource)
    }

    @Provides
    @Singleton
    fun providesGetLoginUseCase(
        footieRepository: FootieRepository
    ): GetPlayerUsecases =
        GetPlayerUsecasesImpl(footieRepository)

    @Provides
    @Singleton
    fun providesPostPlayerUseCase(
        footieRepository: FootieRepository
    ): PostPlayerUsecases =
        PostPlayerUsecasesImpl(footieRepository)

    @Provides
    @Singleton
    fun providesUpdatePlayerUseCase(
        footieRepository: FootieRepository
    ): PutPlayerUsecases =
        PutPlayerUsecasesImpl(footieRepository)

    @Provides
    @Singleton
    fun providesGetPlayersByFootballMatchUsecase(
        footieRepository: FootieRepository
    ): GetPlayersByFootballMatchUsecases =
        GetPlayersByFootballMatchUsecasesImpl(footieRepository)

    @Provides
    @Singleton
    fun providesGetFootballMatchUseCase(
        footieRepository: FootieRepository
    ): GetFootballMatchUsecases =
        GetFootballMatchUsecasesImpl(footieRepository)

    @Provides
    @Singleton
    fun providesPostFootballMatchUsecase(
        footieRepository: FootieRepository
    ): PostFootballMatchUsecases =
        PostFootballMatchUsecasesImpl(footieRepository)

    @Provides
    @Singleton
    fun providesPutFootballMatchUsecase(
        footieRepository: FootieRepository
    ): PutFootballMatchUsecases =
        PutFootballMatchUsecasesImpl(footieRepository)
}