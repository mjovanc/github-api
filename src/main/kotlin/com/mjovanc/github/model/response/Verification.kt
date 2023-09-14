package com.mjovanc.github.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Verification(
    val verified: Boolean,
    val reason: String?,
    val signature: String?,
    val payload: String?
)
