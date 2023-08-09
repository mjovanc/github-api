package com.mjovanc.github.model.response

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val id: Int,
    @SerialName("node_id") val nodeId: String,
    val url: String,
    @SerialName("html_url") val htmlUrl: String,
    val name: String,
    val slug: String,
    val description: String,
    val privacy: String,
    val permission: String,
    @SerialName("notification_setting") val notificationSetting: String,
    @SerialName("members_url") val membersUrl: String,
    @SerialName("repositories_url") val repositoriesUrl: String,
    @Contextual val parent: Any?
)
