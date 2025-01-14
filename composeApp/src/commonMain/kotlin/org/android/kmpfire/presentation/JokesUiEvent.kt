package org.android.kmpfire.presentation

sealed class JokesUiEvent {
    data object GetPokemonList: JokesUiEvent()
}