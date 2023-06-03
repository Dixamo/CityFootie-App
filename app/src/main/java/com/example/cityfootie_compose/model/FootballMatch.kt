package com.example.cityfootie_compose.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class FootballMatch(
    //@SerializedName("latitude")
    var latitude: Double? = null,

    //@SerializedName("surnames")
    var longitude: Double? = null,

    @SerializedName("number_max")
    var numberMax: Int? = null,

    @SerializedName("number_players")
    var number_players: String? = null,

    //@SerializedName("date")
    var date: Timestamp? = null,
)
