package com.mjovanc.github.model.response.collaborators

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryInvitation(
    val id: Long,
    @SerialName("node_id") val nodeId: String,
    val repository: Repository,
    val invitee: User,
    val inviter: User,
    val permissions: String,
    @SerialName("created_at") val createdAt: String,
    val url: String,
    @SerialName("html_url") val htmlUrl: String
)