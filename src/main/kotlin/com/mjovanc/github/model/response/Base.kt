package com.mjovanc.github.model.response

import com.mjovanc.github.model.response.collaborators.Repository
import kotlinx.serialization.Serializable

@Serializable
data class Base(
    val label: String,
    val ref: String,
    val sha: String,
    val user: User,
    val repo: Repository
)
