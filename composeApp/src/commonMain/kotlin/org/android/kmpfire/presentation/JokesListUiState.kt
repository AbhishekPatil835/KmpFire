package org.android.kmpfire.presentation

import org.android.kmpfire.domain.model.Joke


data class JokesListUiState(
    var data: List<Joke> = emptyList(),
    var error: String = "",
    var loading: Boolean = false
)