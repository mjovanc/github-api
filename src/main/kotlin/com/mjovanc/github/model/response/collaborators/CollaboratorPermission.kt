package com.mjovanc.github.model.response.collaborators

import com.mjovanc.github.model.response.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollaboratorPermission(
    @SerialName("permission") val permission: String,
    @SerialName("role_name") val roleName: String,
    @SerialName("user") val user: User
)