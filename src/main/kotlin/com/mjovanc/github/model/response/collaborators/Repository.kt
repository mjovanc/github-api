package com.mjovanc.github.model.response.collaborators

import com.mjovanc.github.model.response.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val id: Long,
    @SerialName("node_id") val nodeId: String,
    val name: String,
    @SerialName("full_name") val fullName: String,
    val owner: User,
    val private: Boolean,
    @SerialName("html_url") val htmlUrl: String,
    val description: String?,
    val fork: Boolean,
    val url: String,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("pushed_at") val pushedAt: String? = null,
    val homepage: String? = null,
    val size: Long? = null,
    @SerialName("stargazers_count") val stargazersCount: Long? = null,
    @SerialName("watchers_count") val watchersCount: Long? = null,
    val language: String? = null,
    @SerialName("forks_count") val forksCount: Long? = null,
    @SerialName("open_issues_count") val openIssuesCount: Long? = null,
    val masterBranch: String? = null,
    val defaultBranch: String? = null,
    val score: Double? = null
)
