package com.example.cityfootie_compose.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class FootballMatch(

    var latitude: Double? = null,

    var longitude: Double? = null,

    @SerializedName("number_max")
    var numberMax: Int? = null,

    @SerializedName("number_players")
    var numberPlayers: Int? = null,

    var date: Timestamp? = null,
)
