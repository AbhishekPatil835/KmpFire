package org.android.kmpfire.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JokesResponse(
    val amount: Int,
    val error: Boolean,
    val jokes: List<Joke>
)