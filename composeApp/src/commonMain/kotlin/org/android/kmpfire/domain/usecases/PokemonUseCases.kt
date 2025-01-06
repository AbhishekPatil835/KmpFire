package org.android.kmpfire.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.android.Response
import org.android.Result
import org.android.kmpfire.data.repo.PokemonRepo
import org.android.kmpfire.domain.model.PokemonResponse

class PokemonUseCases(private val pokemonRepo: PokemonRepo) {


    suspend fun invoke(): Flow<Response<PokemonResponse>> = pokemonRepo.getPokemonList()
        .map { result ->
            when (result) {
                is Result.Loading -> Response.loading()
                is Result.Success -> Response.success(result.data)
                is Result.Failure -> Response.error(result.msg ?: "Unknown Error")
            }
        }
}