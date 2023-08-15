package com.mjovanc.github.model.request.pulls

import kotlinx.serialization.Serializable

@Serializable
data class CreatePullRequest(
    val title: String,
    val body: String,
    val head: String,
    val base: String
)
