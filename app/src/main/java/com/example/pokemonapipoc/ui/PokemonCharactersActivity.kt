package com.example.pokemonapipoc.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.pokemonapipoc.adapter.PokemonCharactersAdapter
import com.example.pokemonapipoc.adapter.PokemonImagesAdapter
import com.example.pokemonapipoc.databinding.ActivityMainBinding
import com.example.pokemonapipoc.model.ImagesModel
import com.example.pokemonapipoc.model.PokemonCharacters
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


    private var imagesModel: ImagesModel? = null
    lateinit var viewPagerAdapter: PokemonImagesAdapter
    lateinit var indicator: CircleIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        upDateUi()
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