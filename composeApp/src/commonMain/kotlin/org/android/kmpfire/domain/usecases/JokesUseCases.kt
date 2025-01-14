package org.android.kmpfire.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.android.Response
import org.android.Result
import org.android.doOnFailure
import org.android.doOnLoading
import org.android.doOnSuccess
import org.android.kmpfire.data.repo.JokesRepo
import org.android.kmpfire.domain.model.JokesResponse

class JokesUseCases(private val jokesRepo: JokesRepo) {
    suspend fun invoke(): Flow<Response<JokesResponse>> = jokesRepo.getPokemonList(amount = 10, type = "pokemon")
        .doOnLoading {
            // Perform actions on loading like updating task progress
        }
        .doOnSuccess { data ->
            // Perform actions when the data is successfully fetched
        }
        .doOnFailure { error ->
            // Perform actions when there is an error like fetching token retry adding to queue etc
        }
        .map { result ->
            when (result) {
                is Result.Loading -> Response.loading()
                is Result.Success -> Response.success(result.data)
                is Result.Failure -> Response.error(result.msg ?: "Unknown Error")
            }
        }
}

