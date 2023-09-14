package com.mjovanc.github.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileChange(
    val sha: String,
    val filename: String,
    val status: String,
    val additions: Int,
    val deletions: Int,
    val changes: Int,
    @SerialName("blob_url") val blobUrl: String,
    @SerialName("raw_url") val rawUrl: String,
    @SerialName("contents_url") val contentsUrl: String,
    val patch: String
)
