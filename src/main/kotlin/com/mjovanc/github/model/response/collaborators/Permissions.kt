package com.mjovanc.github.model.response.collaborators

import kotlinx.serialization.Serializable

@Serializable
data class Permissions(
    val admin: Boolean,
    val maintain: Boolean,
    val push: Boolean,
    val triage: Boolean,
    val pull: Boolean
)