package com.example.cityfootie_compose.model

import java.util.Date

data class Player(
    private val id: Int? = null,
    private val name: String? = null,
    private val firstSurname: String? = null,
    private val secondSurname: String? = null,
    private val birthDate: Date? = null,
    private val username: String? = null,
    private val email: String? = null,
    private val password: String? = null,
    private val dorsal: Int? = null,
    private val favoritePlayer: String? = null,
    private val favoriteTeam: String? = null
)
