package com.example.pokemonapipoc

import android.app.Application
import com.example.pokemonapipoc.di.module.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApi:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokemonApi)
            modules(Test.modules())
        }
    }
}