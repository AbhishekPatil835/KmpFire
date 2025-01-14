package org.android.kmpfire.di

import org.android.kmpfire.data.JokesRepoImpl
import org.android.kmpfire.data.repo.JokesRepo
import org.android.kmpfire.domain.usecases.JokesUseCases
import org.android.kmpfire.expects.EnvironmentManager
import org.android.kmpfire.httpClient.HttpClientFactory
import org.android.kmpfire.presentation.viewmodel.MainViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val sharedModule = module {
    single { EnvironmentManager }
    singleOf(::HttpClientFactory)
    singleOf(::JokesRepoImpl).bind<JokesRepo>()
    singleOf(::JokesUseCases)
    viewModelOf(::MainViewModel)
}

