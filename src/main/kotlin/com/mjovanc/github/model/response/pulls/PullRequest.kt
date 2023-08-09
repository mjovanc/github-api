package com.mjovanc.github.model.response.pulls

import com.mjovanc.github.model.response.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PullRequest(
    val url: String,
    val id: Int,
    @SerialName("node_id") val nodeId: String,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("diff_url") val diffUrl: String,
    @SerialName("patch_url") val patchUrl: String,
    @SerialName("issue_url") val issueUrl: String,
    @SerialName("commits_url") val commitsUrl: String,
    @SerialName("review_comments_url") val reviewCommentsUrl: String,
    @SerialName("review_comment_url") val reviewCommentUrl: String,
    @SerialName("comments_url") val commentsUrl: String,
    @SerialName("statuses_url") val statusesUrl: String,
    val number: Int,
    val state: String,
    val locked: Boolean,
    val title: String,
    val user: User,
    val body: String,
    val labels: List<Label>,
    val milestone: Milestone?,
    @SerialName("active_lock_reason") val activeLockReason: String?,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("closed_at") val closedAt: String?,
    @SerialName("merged_at") val mergedAt: String?,
    @SerialName("merge_commit_sha") val mergeCommitSha: String?,
    val assignee: User?,
    val assignees: List<User>,
    @SerialName("requested_reviewers") val requestedReviewers: List<User>,
    @SerialName("requested_teams") val requestedTeams: List<Team>,
    val head: Head,
    val base: Base
)
