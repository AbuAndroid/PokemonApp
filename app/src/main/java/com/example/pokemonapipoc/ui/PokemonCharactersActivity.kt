package com.example.pokemonapipoc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.pokemonapipoc.adapter.PokemonCharactersAdapter
import com.example.pokemonapipoc.databinding.ActivityMainBinding
import com.example.pokemonapipoc.model.PokemonCharacters
import com.example.pokemonapipoc.model.newsapimodel.Articles
import com.example.pokemonapipoc.utils.fetchDataFromAssert
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.relex.circleindicator.CircleIndicator
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonCharactersActivity : AppCompatActivity() {

    private val pokemonViewModel:PokemonViewModel by viewModel()
    private var binding:ActivityMainBinding? = null
    private val pokemonAdapter: PokemonCharactersAdapter by lazy {
        PokemonCharactersAdapter(
            pokeMonList = arrayListOf()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        upDateUi()
        val jsonFileString = fetchDataFromAssert(applicationContext,"newsApi.json")
        if (jsonFileString != null) {
            Log.i("data",jsonFileString)
        }

        val gson = Gson()
        val listPersonType = object :TypeToken<Articles>() {}.type

        val persons:List<Articles> = gson.fromJson(jsonFileString,listPersonType)
        persons.forEachIndexed{idx,article -> Log.e("data","> Item $idx:\n$article")}
        setUpObserver()

    }

    private fun setUpObserver() {
        pokemonViewModel.pokeMonCharacterList.observe(this,::setToList)
        pokemonViewModel.loader.observe(this,::handleLoading)
        pokemonViewModel.error.observe(this,::handleError)
    }

    private fun handleError(error: String?) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

    private fun handleLoading(loading: Boolean?) {
        binding?.uiPbLoading?.visibility = if(loading==true) View.VISIBLE else View.GONE
    }

    private fun setToList(pokemonCharacters: List<PokemonCharacters>?) {
        pokemonCharacters?.let { pokemonAdapter.onCharactersChanged(it) }
    }

    private fun upDateUi() {
        binding?.uiRvPokemonItems?.adapter = pokemonAdapter
    }
}