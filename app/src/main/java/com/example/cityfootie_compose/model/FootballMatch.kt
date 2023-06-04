package com.example.cityfootie_compose.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class FootballMatch(
    //@SerializedName("latitude")
    var latitude: Double? = null,

    //@SerializedName("longitude")
    var longitude: Double? = null,

    @SerializedName("number_max")
    var numberMax: Int? = null,

    @SerializedName("number_players")
    var numberPlayers: Int? = null,

    //@SerializedName("date")
    var date: Timestamp? = null,
)
