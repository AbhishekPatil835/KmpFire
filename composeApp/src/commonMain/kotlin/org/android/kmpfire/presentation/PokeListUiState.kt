package org.android.kmpfire.presentation

import org.android.kmpfire.domain.model.Pokemon


data class PokeListUiState(
    var data: List<Pokemon> = emptyList(),
    var error: String = "",
    var loading: Boolean = false
)