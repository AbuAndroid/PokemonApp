package com.example.pokemonapipoc.mapper


import com.example.pokemonapipoc.model.PokemonModel
import com.example.pokemonapipoc.model.Results
import com.example.pokemonapipoc.utils.CustomResponse
import com.example.pokemonapipoc.utils.LocalException
import com.example.pokemonapipoc.wareHouse.Constants.Companion.ERROR_SERVER
import retrofit2.Response

object PokemonResponseMapper {
    fun mapp(pokeMonDataResponse: Response<PokemonModel>):CustomResponse<List<Results>,LocalException>{
        return if(pokeMonDataResponse.isSuccessful && pokeMonDataResponse.code()==200){
            CustomResponse.Success(pokeMonDataResponse.body()?.results ?: arrayListOf())
        }else{
            CustomResponse.Failure(LocalException(ERROR_SERVER))
        }
    }
    fun map(pokeMonDataResponse:Response<PokemonModel>): CustomResponse<List<String>, LocalException> {
        return if(pokeMonDataResponse.isSuccessful && pokeMonDataResponse.code() == 200){
            CustomResponse.Success(getPokemonCharacterLink(pokeMonDataResponse.body()?.results) ?: arrayListOf())
        }else{
            CustomResponse.Failure(LocalException(ERROR_SERVER))
        }
    }

    private fun getPokemonCharacterLink(results: List<Results>?): List<String>? {
        return results?.map {
            it.url
        }
    }


}