package com.example.cityfootie_compose.model.prueba

data class User (
    val name: String,
    val lastName: String,
    val city: String,
    val thumbnail: String,
    val id: Int = 0
)