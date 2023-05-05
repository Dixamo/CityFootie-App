package com.example.cityfootie_compose.repository

import com.example.cityfootie_compose.datasource.RestDataSource
import com.example.cityfootie_compose.model.prueba.User
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser(): User
}

class UserRepositoryImpl @Inject constructor(
    private val dataSource: RestDataSource
) : UserRepository {
    override suspend fun getNewUser(): User {
        val name = dataSource.getUserName().results[0].name!!
        val location = dataSource.getUserLocation().results[0].location!!
        val picture = dataSource.getUserPicture().results[0].picture!!
        return User(name.first, name.last, location.city, picture.thumbnail)
    }
}