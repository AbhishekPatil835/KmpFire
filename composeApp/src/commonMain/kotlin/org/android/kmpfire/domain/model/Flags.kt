package org.android.kmpfire.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    val explicit: Boolean,
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)