package com.mjovanc.github.model.response.collaborators

import kotlinx.serialization.Serializable

@Serializable
data class CreatePullRequest(
    val title: String,
    val body: String,
    val head: String,
    val base: String
)
