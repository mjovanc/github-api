package com.mjovanc.github.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Commit(
    val url: String,
    val sha: String,
    @SerialName("node_id") val nodeId: String,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("comments_url") val commentsUrl: String,
    val commit: CommitInfo,
    val author: User,
    val committer: User,
    val parents: List<Parent>
)
