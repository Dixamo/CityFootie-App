package com.example.cityfootie_compose.model.prueba

data class ApiResponse (
    val results: List<Results> = emptyList()
)

data class Results (
    val name: UserName?,
    val location: UserLocation?,
    val picture: UserPicture?
)