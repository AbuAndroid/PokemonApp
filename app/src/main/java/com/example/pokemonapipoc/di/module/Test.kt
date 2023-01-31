package com.example.pokemonapipoc.di.module

import com.example.pokemonapipoc.repository.PokemonRespository
import com.example.pokemonapipoc.ui.PokemonViewModel
import com.example.pokemonapipoc.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object Test{
    fun modules() = repomodule + viewModelModule + commonModule
}

val repomodule = module {
    single{
        PokemonRespository(get())
    }
}

val viewModelModule = module {
    viewModel{
        PokemonViewModel(get(),get())
    }
}

val commonModule = module{
    single{
        NetworkHelper(androidContext())
    }
    single{
        RestHelper.client
    }
}