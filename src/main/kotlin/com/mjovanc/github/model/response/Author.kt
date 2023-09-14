package com.mjovanc.github.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val name: String,
    val email: String,
    val date: String
)
