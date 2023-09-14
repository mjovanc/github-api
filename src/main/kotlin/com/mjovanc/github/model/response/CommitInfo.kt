package com.mjovanc.github.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommitInfo(
    val url: String,
    val author: Author,
    val committer: Author,
    val message: String,
    val tree: Tree,
    @SerialName("comment_count") val commentCount: Int,
    val verification: Verification
)
