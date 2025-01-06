package org.android.kmpfire.data.repo

import kotlinx.coroutines.flow.Flow
import org.android.Result
import org.android.kmpfire.domain.model.PokemonResponse

interface PokemonRepo {
    suspend fun getPokemonList(): Flow<Result<PokemonResponse>>
}