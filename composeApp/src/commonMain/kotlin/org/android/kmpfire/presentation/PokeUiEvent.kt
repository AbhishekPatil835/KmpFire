package org.android.kmpfire.presentation

sealed class PokeUiEvent {
    data object GetPokemonList: PokeUiEvent()
}