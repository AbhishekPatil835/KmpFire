package org.android.kmpfire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.android.Response
import org.android.kmpfire.domain.usecases.PokemonUseCases
import org.android.kmpfire.presentation.PokeListUiState
import org.android.kmpfire.presentation.PokeUiEvent

class MainViewModel(private val pokemonUseCases: PokemonUseCases) : ViewModel() {
    private val _uiState: MutableStateFlow<PokeListUiState> = MutableStateFlow(PokeListUiState())
    val uiState = _uiState.asStateFlow()
        .onStart { onEvent(PokeUiEvent.GetPokemonList) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PokeListUiState())
        /*init {
            onEvent(PokeUiEvent.GetPokemonList)
        }*/

    fun onEvent(event: PokeUiEvent) {
        when (event) {
            is PokeUiEvent.GetPokemonList -> {
                getPokemonList()
            }
        }
    }

    private fun getPokemonList() {
        viewModelScope.launch {

            pokemonUseCases.invoke() // Flow<Response<PokemonResponse>>
                .collect { response ->
                    when (response.status) {
                        Response.Status.LOADING -> {
                            _uiState.value = _uiState.value.copy(
                                loading = true,
                                error = "",
                                data = emptyList()
                            )
                        }

                        Response.Status.SUCCESS -> {
                            _uiState.value = _uiState.value.copy(
                                loading = false,
                                error = "",
                                data = response.data?.results ?: emptyList()
                            )
                        }

                        Response.Status.ERROR -> {
                            _uiState.value = _uiState.value.copy(
                                loading = false,
                                error = response.error ?: "Something went wrong",
                                data = emptyList()
                            )
                        }
                    }
                }
        }
    }

}