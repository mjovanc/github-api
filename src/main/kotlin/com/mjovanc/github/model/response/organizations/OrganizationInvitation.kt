package com.mjovanc.github.model.response.organizations

import com.mjovanc.github.model.response.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrganizationInvitation(
    val id: Long,
    val login: String,
    @SerialName("node_id") val nodeId: String,
    val email: String?,
    val role: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("failed_at") val failedAt: String,
    @SerialName("failed_reason") val failedReason: String,
    val inviter: User,
    @SerialName("team_count") val teamCount: Int,
    @SerialName("invitation_teams_url") val invitationTeamsUrl: String,
    @SerialName("invitation_source") val invitationSource: String
)
