package com.mjovanc.github.client.collaborators

import com.mjovanc.github.client.client
import com.mjovanc.github.model.response.collaborators.Collaborator
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Collaborators Client
 *
 * Implements the GitHub API endpoints seen in
 * https://docs.github.com/en/rest/collaborators
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
class CollaboratorsClient {

    private val logger = LoggerFactory.getLogger("CollaboratorsClient")

    private val properties = Properties()

    private var token = System.getenv("GITHUB_TOKEN")

    init {
        if (token == null) {
            properties.load(CollaboratorsClient::class.java.getResourceAsStream("/github.properties"))
            token = properties.getProperty("token")
        }
    }

    /**
     * List repository collaborators
     *
     * @param owner String
     * @param repo String
     * @param affiliation String
     * @param permission String
     * @param perPage Int
     * @param page Int
     * @see <a href="https://docs.github.com/en/rest/reference/repos#list-repository-collaborators">List repository collaborators</a>
     * @since 0.1.0
     * @return List<Collaborator>? List of collaborators, null if error
     */
    suspend fun listRepositoryCollaborators(
        owner: String,
        repo: String,
        affiliation: String? = null,
        permission: String? = null,
        perPage: Int? = null,
        page: Int? = null
    ): List<Collaborator>? {
        try {
            return client.get("https://api.github.com/repos/$owner/$repo/collaborators") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                parameter("affiliation", affiliation)
                parameter("permission", permission)
                parameter("per_page", perPage)
                parameter("page", page)
            }.body<List<Collaborator>>()
        } catch (e: Exception) {
            logger.error("Error getting list of repository collaborators.", e)
        }

        return null
    }

    /**
     * Check if a user is a repository collaborator
     *
     * @param owner String
     * @param repo String
     * @param username String
     * @see <a href="https://docs.github.com/en/rest/reference/repos#check-if-a-user-is-a-repository-collaborator">Check if a user is a repository collaborator</a>
     * @since 0.1.0
     * @return Boolean? True if user is a collaborator, false if not, null if error
     */
    suspend fun checkIfUserIsARepositoryCollaborator(owner: String, repo: String, username: String): Boolean? {
        try {
            val request = client.get("https://api.github.com/repos/$owner/$repo/collaborators/$username") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.status.value

            return request == 204
        } catch (e: Exception) {
            logger.error("Error with check if a user is a repository collaborator.", e)
        }

        return null
    }

    suspend fun addRepositoryCollaborator() {
        logger.info("addRepositoryCollaborator")
    }

    suspend fun removeRepositoryCollaborator() {
        logger.info("removeRepositoryCollaborator")
    }

    suspend fun getRepositoryPermissionForUser() {
        logger.info("listInvitationsForARepository")
    }

}