package com.example.cityfootie_compose.di

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.cityfootie_compose.datasource.PlayerRepository
import com.example.cityfootie_compose.datasource.PlayerRepositoryImpl
import com.example.cityfootie_compose.datasource.remote.FootieAPI
import com.example.cityfootie_compose.datasource.remote.PlayerRemoteDataSource
import com.example.cityfootie_compose.datasource.remote.PlayerRemoteDataSourceImpl
import com.example.cityfootie_compose.usecases.login.GetLogin
import com.example.cityfootie_compose.usecases.login.GetLoginImpl
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    //@Singleton
    //@Provides
    //@Named("BaseUrl")
    //fun provideBaseUrl() = "https://randomuser.me/api/"
    //
    //
    //
    //
    //

    @Provides
    @Singleton
    fun getInterceptor(/*@ApplicationContext context: Context*/): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            Handler(Looper.getMainLooper()).post {
                //Toast.makeText(context, request.toString(), Toast.LENGTH_LONG).show()
                Log.d("SIGNALCALL ", request.toString())
            }
            //request
            //.addHeader("FootieAPI-Key", "928e842ff4msh9b152a26f3ab1aap173fbdjsn2e110048ce6c")
            //.addHeader("FootieAPI-Host", "twelve-data1.p.rapidapi.com")
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor {
        }.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().serializeNulls().setLenient().create()

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080") // Emulador AndoridStudio 10.0.2.2 // JuanDa 192.168.1.63 // Miguel 192.168.1.132 // Sebas 192.168.1.11
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideFootieService(retrofit: Retrofit): FootieAPI {
        return retrofit.create(FootieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatchers(): DispatcherProvider = DispatcherProviderImpl()

    @Provides
    @Singleton
    fun providePlayerRemoteDataSource(
        footieAPI: FootieAPI,
        dispatcherProvider: DispatcherProvider
    ): PlayerRemoteDataSource {
        return PlayerRemoteDataSourceImpl(footieAPI, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideRepository(
        playerRemoteDataSource: PlayerRemoteDataSource
    ): PlayerRepository {
        return PlayerRepositoryImpl(playerRemoteDataSource)
    }

    @Provides
    @Singleton
    fun providesGetLoginUseCase(playerRepository: PlayerRepository): GetLogin =
        GetLoginImpl(playerRepository)
}