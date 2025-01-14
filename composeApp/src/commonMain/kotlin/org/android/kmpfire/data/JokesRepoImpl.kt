package org.android.kmpfire.data

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.http.path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.android.Result
import org.android.errorBody
import org.android.kmpfire.data.repo.JokesRepo
import org.android.kmpfire.domain.model.JokesResponse
import org.android.kmpfire.httpClient.HttpClientFactory

const val JOKES_API = "joke/Programming"

class JokesRepoImpl(private val httpClientFactory: HttpClientFactory): JokesRepo {

    override suspend fun getPokemonList(amount:Int, type:String)=flow {
        emit(Result.Loading)
        try {
            val response = httpClientFactory.createHttpClient().get {
                url{
                    path(JOKES_API)
                    parameters.append("amount", amount.toString())
                    parameters.append("type", type)
                }

            }
            val pokemonResponse = response.body<JokesResponse>()
            emit(Result.Success(pokemonResponse))
        } catch (e: Exception) {
            if (e is ClientRequestException) {
                val error = e.errorBody<JokesResponse>()
                emit(Result.Failure((error?.error ?: e.response.bodyAsText()).toString()))
            } else {
                emit(Result.Failure(e.message))
            }
        }
    }.catch {
        emit(Result.Failure(it.toString()))
    }.flowOn(Dispatchers.IO)

}