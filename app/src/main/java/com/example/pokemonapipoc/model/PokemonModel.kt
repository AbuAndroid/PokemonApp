package com.example.pokemonapipoc.model


import com.google.gson.annotations.SerializedName

data class PokemonModel(
    val count:Int?,
    val next:String?,
    val results:List<Results>?
)

data class Results(
    val name:String?,
    val url:String
)

data class PokemonCharacters(
    @SerializedName("base_experience")
    val baseExperience:Int?,
    val height:Int?,
    val weight:Int?,
    val name:String?,
    val sprites:Sprites?
)


data class Sprites(
    @SerializedName("back_default")
    val backDefault:String?,
    @SerializedName("back_shiny")
    val backShiny:String?,
    @SerializedName("front_default")
    val frontDefault:String?,
    @SerializedName("front_shiny")
    val frontShiny:String?
)

