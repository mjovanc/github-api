package com.mjovanc.github.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MergeStatus(
    val sha: String,
    val merged: Boolean,
    val message: String
)
