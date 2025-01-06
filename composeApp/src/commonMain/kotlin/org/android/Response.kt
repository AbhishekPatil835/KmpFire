package org.android

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.serialization.SerializationException


data class Response<out T>(
    val status: Status,
    val data: T? = null,
    val error: String? = null
) {
    enum class Status { LOADING, SUCCESS, ERROR }

    companion object {
        fun <T> loading(): Response<T> = Response(Status.LOADING, null, null)
        fun <T> success(data: T? = null): Response<T> = Response(Status.SUCCESS, data, null)
        fun <T> error(error: String): Response<T> = Response(Status.ERROR, null, error)
    }
}


sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val msg: String?) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

fun <T> Flow<Result<T>>.doOnSuccess(action: suspend (T) -> Unit): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Success) {
            action(result.data)
        }
        return@transform emit(result)
    }

fun <T> Flow<Result<T>>.doOnFailure(action: suspend (String?) -> Unit): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Failure) {
            action(result.msg)
        }
        return@transform emit(result)
    }

fun <T> Flow<Result<T>>.doOnLoading(action: suspend () -> Unit): Flow<Result<T>> =
    transform { result ->
        if (result is Result.Loading) {
            action()
        }
        return@transform emit(result)
    }


suspend inline fun <reified E> ResponseException.errorBody(): E? =
    try {
        response.body()
    } catch (e: SerializationException) {
        null
    }