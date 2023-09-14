package com.mjovanc.github.client.collaborators

import com.mjovanc.github.client.client
import com.mjovanc.github.model.response.collaborators.Collaborator
import com.mjovanc.github.model.response.collaborators.CollaboratorPermission
import com.mjovanc.github.model.response.collaborators.RepositoryInvitation
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
        affiliation: String? = "all",
        permission: String? = null,
        perPage: Int? = 30,
        page: Int? = 1
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
            val response = client.get("https://api.github.com/repos/$owner/$repo/collaborators/$username") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.status.value

            return response == 204
        } catch (e: Exception) {
            logger.error("Error checking if a user is a repository collaborator.", e)
        }

        return null
    }

    /**
     * Add repository collaborator
     *
     * @param owner String
     * @param repo String
     * @param username String
     * @param permission String
     * @see <a href="https://docs.github.com/en/rest/reference/repos#add-repository-collaborator">Add repository collaborator</a>
     * @since 0.1.0
     * @return RepositoryInvitation? Repository invitation, null if error
     */
    suspend fun addRepositoryCollaborator(owner: String, repo: String, username: String, permission: String? = "push"):
            RepositoryInvitation? {
        //TODO for some reason it works, but it can not parse the response of JSON to map RepositoryInvitation
        try {
            val payload = Json.encodeToString(mapOf("permission" to permission))
            return client.put("https://api.github.com/repos/$owner/$repo/collaborators/$username") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                setBody(payload)
            }.body<RepositoryInvitation>()
        } catch (e: Exception) {
            logger.error("Error adding repository collaborator.", e)
        }

        return null
    }

    /**
     * Remove repository collaborator
     *
     * @param owner String
     * @param repo String
     * @param username String
     * @see <a href="https://docs.github.com/en/rest/reference/repos#remove-repository-collaborator">Remove repository collaborator</a>
     * @since 0.1.0
     * @return Boolean? True if user is a collaborator, false if not, null if error
     */
    suspend fun removeRepositoryCollaborator(owner: String, repo: String, username: String): Boolean? {
        try {
            val request = client.delete("https://api.github.com/repos/$owner/$repo/collaborators/$username") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.status.value

            return request == 204
        } catch (e: Exception) {
            logger.error("Error removing repository collaborator.", e)
        }

        return null
    }

    /**
     * Get repository permission for user
     *
     * @param owner String
     * @param repo String
     * @param username String
     * @see <a href="https://docs.github.com/en/rest/reference/repos#get-repository-permission-for-a-user">Get repository permission for a user</a>
     * @since 0.1.0
     * @return CollaboratorPermission? Collaborator permission, null if error
     */
    suspend fun getRepositoryPermissionForUser(owner: String, repo: String, username: String): CollaboratorPermission? {
        try {
            return client.get("https://api.github.com/repos/$owner/$repo/collaborators/$username/permission") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.body<CollaboratorPermission>()
        } catch (e: Exception) {
            logger.error("Error with get repository permission for user.", e)
        }

        return null
    }

}