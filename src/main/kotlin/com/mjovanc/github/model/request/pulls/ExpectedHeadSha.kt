package com.mjovanc.github.model.request.pulls

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExpectedHeadSha(
    @SerialName("expected_head_sha") val expectedHeadSha: String
)
