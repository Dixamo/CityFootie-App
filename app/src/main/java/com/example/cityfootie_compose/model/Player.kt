package com.example.cityfootie_compose.model

data class Player(
    //@SerializedName("name")
    var name: String? = null,

    //@SerializedName("surnames")
    var surnames: String? = null,

    //@SerializedName("username")
    var username: String? = null,

    //@SerializedName("email")
    var email: String? = null,

    //@SerializedName("password")
    var password: String? = null,

    //@SerializedName("number")
    var number: Int? = null
)
