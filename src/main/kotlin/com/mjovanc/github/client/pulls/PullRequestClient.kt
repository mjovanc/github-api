package com.mjovanc.github.client.pulls

import com.mjovanc.github.client.client
import com.mjovanc.github.client.collaborators.CollaboratorsClient
import com.mjovanc.github.model.request.pulls.CommitInfo
import com.mjovanc.github.model.request.pulls.CreatePullRequest
import com.mjovanc.github.model.request.pulls.ExpectedHeadSha
import com.mjovanc.github.model.response.Commit
import com.mjovanc.github.model.response.FileChange
import com.mjovanc.github.model.response.MergeStatus
import com.mjovanc.github.model.response.PullRequestUpdate
import com.mjovanc.github.model.response.pulls.PullRequest
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Pull Request Client
 *
 * Implements the GitHub API endpoints seen in
 * https://docs.github.com/en/rest/pulls/pulls
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
class PullRequestClient {

    private val logger = LoggerFactory.getLogger("PullRequestClient")

    private val properties = Properties()

    private var token = System.getenv("GITHUB_TOKEN")

    init {
        if (token == null) {
            properties.load(CollaboratorsClient::class.java.getResourceAsStream("/github.properties"))
            token = properties.getProperty("token")
        }
    }

    /**
     * List pull requests
     *
     * @param owner String
     * @param repo String
     * @param state String
     * @param head String
     * @param base String
     * @param sort String
     * @param direction String
     * @param perPage Int
     * @param page Int
     * @see <a href="https://docs.github.com/en/rest/pulls/pulls?apiVersion=2022-11-28#list-pull-requests">List pull requests</a>
     * @since 0.1.0
     * @return List<PullRequest>? List of collaborators, null if error
     */
    suspend fun listPullRequests(
        owner: String,
        repo: String,
        state: String? = "open",
        head: String? = null,
        base: String? = null,
        sort: String? = "created",
        direction: String? = "desc",
        perPage: Int? = 30,
        page: Int? = 1
    ): List<PullRequest>? {
        try {
            return client.get("https://api.github.com/repos/$owner/$repo/pulls") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                parameter("state", state)
                head?.let { parameter("head", head) }
                base?.let { parameter("base", base) }
                parameter("sort", sort)
                parameter("direction", direction)
                parameter("per_page", perPage)
                parameter("page", page)
            }.body<List<PullRequest>>()
        } catch (e: Exception) {
            logger.error("Error getting list of repository collaborators.", e)
        }

        return null
    }

    /**
     * List pull requests
     *
     * @param owner String
     * @param repo String
     * @param createPullRequest CreatePullRequest
     * @see <a href="https://docs.github.com/en/rest/pulls/pulls?apiVersion=2022-11-28#create-a-pull-request">Create a pull request</a>
     * @since 0.1.0
     * @return PullRequest? null if error
     */
    suspend fun createPullRequest(owner: String, repo: String, pullRequest: CreatePullRequest):
            PullRequest? {
        try {
            val payload = Json.encodeToString(pullRequest)
            val response = client.post("https://api.github.com/repos/$owner/$repo/pulls") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                setBody(payload)
            }

            if (response.status.isSuccess())
                return response.body<PullRequest>()
        } catch (e: Exception) {
            logger.error("Error creating pull request.", e)
        }

        return null
    }

    suspend fun getPullRequest(owner: String, repo: String, pullNumber: Long): PullRequest? {
        try {
            return client.get("https://api.github.com/repos/$owner/$repo/pulls/$pullNumber") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.body<PullRequest>()
        } catch (e: Exception) {
            logger.error("Error getting pull request.", e)
        }

        return null
    }

    suspend fun updatePullRequest(owner: String, repo: String, pullNumber: Long, pullRequest: CreatePullRequest):
            PullRequest? {
        try {
            val payload = Json.encodeToString(pullRequest)
            return client.patch("https://api.github.com/repos/$owner/$repo/pulls") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                setBody(payload)
            }.body<PullRequest>()
        } catch (e: Exception) {
            logger.error("Error update pull request.", e)
        }

        return null
    }

    suspend fun listCommitsOnPullRequest(owner: String, repo: String, pullNumber: Long): List<Commit>? {
        try {
            return client.get("https://api.github.com/repos/$owner/$repo/pulls/$pullNumber/commits") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.body<List<Commit>>()
        } catch (e: Exception) {
            logger.error("Error listing commits on pull request.", e)
        }

        return null
    }

    suspend fun listFilesOfPullRequest(owner: String, repo: String, pullNumber: Long): List<FileChange>? {
        try {
            return client.get("https://api.github.com/repos/$owner/$repo/pulls/$pullNumber/files") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.body<List<FileChange>>()
        } catch (e: Exception) {
            logger.error("Error listing files of pull request.", e)
        }

        return null
    }

    suspend fun isPullRequestMerged(owner: String, repo: String, pullNumber: Long): Boolean? {
        try {
            val response = client.post("https://api.github.com/repos/$owner/$repo/pulls/$pullNumber/merge") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.status.value

            return response == 204
        } catch (e: Exception) {
            logger.error("Error checking if pull request is merged.", e)
        }

        return null
    }

    suspend fun mergePullRequest(owner: String, repo: String, pullNumber: Long, commitInfo: CommitInfo): MergeStatus? {
        try {
            val payload = Json.encodeToString(commitInfo)
            return client.put("https://api.github.com/repos/$owner/$repo/pulls/$pullNumber/merge") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                setBody(payload)
            }.body<MergeStatus>()
        } catch (e: Exception) {
            logger.error("Error merging pull request.", e)
        }

        return null
    }

    suspend fun updateBranchOfPullRequest(owner: String, repo: String, pullNumber: Long, expectedHeadSha: ExpectedHeadSha):
            PullRequestUpdate? {
        try {
            val payload = Json.encodeToString(expectedHeadSha)
            return client.put("https://api.github.com/repos/$owner/$repo/pulls/$pullNumber/update-branch") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                setBody(payload)
            }.body<PullRequestUpdate>()
        } catch (e: Exception) {
            logger.error("Error updating branch of pull request.", e)
        }

        return null
    }

}