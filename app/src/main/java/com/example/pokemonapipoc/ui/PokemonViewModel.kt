package com.example.pokemonapipoc.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapipoc.model.PokemonCharacters
import com.example.pokemonapipoc.repository.PokemonRespository
import com.example.pokemonapipoc.utils.CustomResponse
import com.example.pokemonapipoc.utils.NetworkHelper
import kotlinx.coroutines.launch


class PokemonViewModel(
    private val repository: PokemonRespository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val pokemonCharactersLd = MutableLiveData<List<PokemonCharacters>>()
    private var errorLd = MutableLiveData<String>() //Handle Error From Served
    private val loaderLd = MutableLiveData<Boolean>() //Handle Loading Event

    val error: LiveData<String> = errorLd
    val loader: LiveData<Boolean> = loaderLd
    val pokeMonCharacterList : LiveData<List<PokemonCharacters>> = pokemonCharactersLd
    private val allPokeMonCharacters = arrayListOf<PokemonCharacters>()

    init{
        fetchAllPokemonCharacter()
    }
    fun fetchAllPokemonCharacter() {
        if(networkHelper.isNetworkConnected()){
            loaderLd.value = true
            viewModelScope.launch {
                when(val response = repository.getAllPokemonListLink()){
                    is CustomResponse.Success -> {
                        fetchAllCharactersFromLink(response.data)
                    }
                    is CustomResponse.Failure ->{
                        errorLd.value = response.error.message
                    }
                }.also { loaderLd.value = false }
            }
        }
    }

    fun fetchAllCharactersFromLink(data: List<String>) {
        data.forEach {
            viewModelScope.launch {
                fetchPokemonCharacters(it)
                when(val responseAttribute = repository.getAllPokemonAttribute(it)){
                    is CustomResponse.Success -> {
                        responseAttribute.data?.let { it1 -> allPokeMonCharacters.add(it1) }
                        pokemonCharactersLd.value = allPokeMonCharacters
                    }
                    else -> {}
                }.also {
                    loaderLd.value = false
                }
            }
        }
    }

    private suspend fun fetchPokemonCharacters(it: String) {
        repository.getAllPokemonAttribute(it)
    }
}