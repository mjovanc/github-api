package com.mjovanc.github.model.request.pulls

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommitInfo(
    @SerialName("commit_title") val commitTitle: String,
    @SerialName("commit_message") val commitMessage: String
)
