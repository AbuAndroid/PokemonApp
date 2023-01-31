package com.example.pokemonapipoc.repository

import com.example.pokemonapipoc.mapper.PokemonAttributeMapper
import com.example.pokemonapipoc.mapper.PokemonResponseMapper
import com.example.pokemonapipoc.model.PokemonCharacters
import com.example.pokemonapipoc.model.Results
import com.example.pokemonapipoc.services.PokeMonApiService
import com.example.pokemonapipoc.utils.CustomResponse
import com.example.pokemonapipoc.utils.LocalException
import com.example.pokemonapipoc.wareHouse.Constants


class PokemonRespository(val pokeMonApiService: PokeMonApiService) {

    suspend fun getAllPk():CustomResponse<List<Results>,LocalException> = PokemonResponseMapper.mapp(
        pokeMonApiService.getPokeMonList(Constants.LIMIT,Constants.OFFSET)
    )
    suspend fun getAllPokemonListLink(): CustomResponse<List<String>, LocalException> =
        PokemonResponseMapper.map(
            pokeMonApiService.getPokeMonList(Constants.LIMIT,Constants.OFFSET)
        )

    suspend fun getAllPokemonAttribute(url: String):CustomResponse<PokemonCharacters?,LocalException> =
        PokemonAttributeMapper.attributeMap(
            pokeMonApiService.getPokemonAttribute(url)
        )
}