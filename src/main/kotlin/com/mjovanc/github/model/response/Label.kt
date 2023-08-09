package com.mjovanc.github.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Label(
    val id: Int,
    @SerialName("node_id") val nodeId: String,
    val url: String,
    val name: String,
    val description: String,
    val color: String,
    val default: Boolean
)
