package com.example.cityfootie_compose.di;

import com.example.cityfootie_compose.repository.UserRepository
import com.example.cityfootie_compose.repository.UserRepositoryImpl
import javax.inject.Named
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Provides
    @Named("BaseUrl")
    abstract fun userRepository(repository: UserRepositoryImpl): UserRepository
}