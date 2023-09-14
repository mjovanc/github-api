package com.mjovanc.github.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PullRequestUpdate(
    val message: String,
    val url: String
)
