package com.mjovanc.github.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Parent(
    val url: String,
    val sha: String
)
