package com.example.pokemonapipoc.mapper

import android.provider.SyncStateContract

import com.example.pokemonapipoc.model.PokemonCharacters
import com.example.pokemonapipoc.utils.CustomResponse
import com.example.pokemonapipoc.utils.LocalException
import com.example.pokemonapipoc.wareHouse.Constants.Companion.ERROR_SERVER
import retrofit2.Response

object PokemonAttributeMapper {
    fun attributeMap(data: Response<PokemonCharacters>): CustomResponse<PokemonCharacters?, LocalException> {
        return if(data.isSuccessful && data.code() == 200){
            CustomResponse.Success(data.body())
        }else{
            CustomResponse.Failure(LocalException(ERROR_SERVER))
        }
    }
}