package com.example.finalapp

import com.google.gson.annotations.SerializedName

data class Items(

    val userId: Int,
    val id: Int,
    val title: String,

    @SerializedName("body")
    val subtitle: String

)
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String // Include the URL if you need it later
)
