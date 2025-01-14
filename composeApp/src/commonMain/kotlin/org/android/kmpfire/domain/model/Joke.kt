package org.android.kmpfire.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Joke(
    val category: String,
    val flags: Flags,
    val id: Int,
    val joke: String?,
    val lang: String,
    val safe: Boolean,
    val type: String
)