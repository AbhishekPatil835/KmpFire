package org.android.kmpfire.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.android.Response
import org.android.kmpfire.domain.usecases.JokesUseCases
import org.android.kmpfire.presentation.JokesListUiState
import org.android.kmpfire.presentation.JokesUiEvent

class MainViewModel(private val jokesUseCases: JokesUseCases) : ViewModel() {
    private val _uiState: MutableStateFlow<JokesListUiState> = MutableStateFlow(JokesListUiState())
    val uiState = _uiState.asStateFlow()
        .onStart { onEvent(JokesUiEvent.GetPokemonList) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), JokesListUiState())

        /*init {
            onEvent(JokesUiEvent.GetPokemonList)
        }*/

    fun onEvent(event: JokesUiEvent) {
        when (event) {
            is JokesUiEvent.GetPokemonList -> {
                getPokemonList()
            }
        }
    }

    private fun getPokemonList() {
        viewModelScope.launch {

            jokesUseCases.invoke() // Flow<Response<PokemonResponse>>
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
                                data = response.data?.jokes ?: emptyList()
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