package com.mjovanc.github.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Milestone(
    val url: String,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("labels_url") val labelsUrl: String,
    val id: Int,
    @SerialName("node_id") val nodeId: String,
    val number: Int,
    val state: String,
    val title: String,
    val description: String,
    val creator: User,
    @SerialName("open_issues") val openIssues: Int,
    @SerialName("closed_issues") val closedIssues: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("closed_at") val closedAt: String?,
    @SerialName("due_on") val dueOn: String
)
