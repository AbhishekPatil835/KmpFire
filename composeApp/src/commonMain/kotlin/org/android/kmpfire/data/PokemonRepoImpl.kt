package org.android.kmpfire.data

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.android.Result
import org.android.errorBody
import org.android.kmpfire.data.repo.PokemonRepo
import org.android.kmpfire.domain.model.PokemonResponse
import org.android.kmpfire.expects.httpClient

const val POKEMON_API = "pokemon"

class PokemonRepoImpl: PokemonRepo {

    override suspend fun getPokemonList()=flow {
        emit(Result.Loading)
        try {
            val response = httpClient.get {
                url(POKEMON_API)
            }
            val pokemonResponse = response.body<PokemonResponse>()
            emit(Result.Success(pokemonResponse))
        } catch (e: Exception) {
            if (e is ClientRequestException) {
                val error = e.errorBody<PokemonResponse>()
                emit(Result.Failure(error?.next ?: e.response.bodyAsText()))
            } else {
                emit(Result.Failure(e.message))
            }
        }
    }.catch {
        emit(Result.Failure(it.toString()))
    }.flowOn(Dispatchers.IO)

}