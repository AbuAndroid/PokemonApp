package com.example.pokemonapipoc.services

import com.example.pokemonapipoc.model.PokemonCharacters
import com.example.pokemonapipoc.model.PokemonModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApiService {

    @GET("api/v2/pokemon")
    suspend fun getPokeMonList(@Query("limit")limit:Int,@Query("offset")offset:Int):Response<PokemonModel>

    @GET
    suspend fun getPokemonAttribute(@Url url: String): Response<PokemonCharacters>
//
//    @GET("api/v2/pokemon/")
//    suspend fun getPokeMonCharacters():Response<PokemonCharacters>

}