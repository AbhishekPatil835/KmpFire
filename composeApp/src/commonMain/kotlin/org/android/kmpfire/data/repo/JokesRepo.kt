package org.android.kmpfire.data.repo

import kotlinx.coroutines.flow.Flow
import org.android.Result
import org.android.kmpfire.domain.model.JokesResponse

interface JokesRepo {
    suspend fun getPokemonList(amount:Int, type:String): Flow<Result<JokesResponse>>
}