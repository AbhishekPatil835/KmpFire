package org.android.kmpfire.di

import org.android.kmpfire.data.PokemonRepoImpl
import org.android.kmpfire.data.repo.PokemonRepo
import org.android.kmpfire.domain.usecases.PokemonUseCases
import org.android.kmpfire.viewmodel.MainViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val sharedModule = module {
    singleOf(::PokemonRepoImpl).bind<PokemonRepo>()
    singleOf(::PokemonUseCases)
    viewModelOf(::MainViewModel)
}

