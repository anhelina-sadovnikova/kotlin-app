package com.example.finalapp

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
data class PokemonDetail(
    val height: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val types: List<Type>,
    val sprites: Sprites
)
data class Sprites(
    val front_default: String?
)
data class Ability(
    val ability: NamedAPIResource
)
data class Type(
    val type: NamedAPIResource
)
data class NamedAPIResource(
    val name: String,
    val url: String
)
