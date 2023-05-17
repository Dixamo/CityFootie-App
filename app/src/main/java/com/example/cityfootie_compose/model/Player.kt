package com.example.cityfootie_compose.model

import java.util.*

data class Player(
    var id: Int? = null,
    var name: String? = null,
    var firstSurname: String? = null,
    var secondSurname: String? = null,
    var birthDate: Date? = null,
    var username: String? = null,
    var email: String? = null,
    var password: String? = null,
    var number: Int? = null,
    var favoritePlayer: String? = null,
    var favoriteTeam: String? = null
)
