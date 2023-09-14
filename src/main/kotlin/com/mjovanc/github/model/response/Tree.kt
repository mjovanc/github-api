package com.mjovanc.github.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Tree(
    val url: String,
    val sha: String
)
