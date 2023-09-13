package com.mjovanc.github.client.collaborators

import com.mjovanc.github.client.client
import com.mjovanc.github.model.response.collaborators.RepositoryInvitation
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Invitations Client
 *
 * Implements the GitHub API endpoints seen in
 * https://docs.github.com/en/rest/invitations
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
class InvitationsClient {

    private val logger = LoggerFactory.getLogger("InvitationsClient")

    private val properties = Properties()

    private var token = System.getenv("GITHUB_TOKEN")

    init {
        if (token == null) {
            properties.load(InvitationsClient::class.java.getResourceAsStream("/github.properties"))
            token = properties.getProperty("token")
        }
    }

    /**
     * List repository invitations
     *
     * @param owner String
     * @param repo String
     * @param perPage Int
     * @param page Int
     * @see <a href="https://docs.github.com/en/rest/reference/repos#list-repository-invitations">List repository invitations</a>
     * @since 0.1.0
     * @return List<RepositoryInvitation>? List of invitations, null if error
     */
    suspend fun listRepositoryInvitations(owner: String, repo: String, perPage: Int? = 30, page: Int? = 1):
            List<RepositoryInvitation>? {
        try {
            return client.get("https://api.github.com/repos/$owner/$repo/invitations") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                parameter("per_page", perPage)
                parameter("page", page)
            }.body<List<RepositoryInvitation>>()
        } catch (e: Exception) {
            logger.error("Error getting list of repository collaborators.", e)
        }

        return null
    }

    /**
     * Update a repository invitation
     *
     * @param owner String
     * @param repo String
     * @param invitationId Long
     * @see <a href="https://docs.github.com/en/rest/reference/repos#get-a-repository-invitation">Get a repository invitation</a>
     * @since 0.1.0
     * @return RepositoryInvitation? Invitation, null if error
     */
    suspend fun updateRepositoryInvitation(owner: String, repo: String, invitationId: Long, permission: String?):
            RepositoryInvitation? {
        try {
            val payload = Json.encodeToString(mapOf("permission" to permission))
            return client.patch("https://api.github.com/repos/$owner/$repo/invitations/$invitationId") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                setBody(payload)
            }.body<RepositoryInvitation>()
        } catch (e: Exception) {
            logger.error("Error updating repository invitation.", e)
        }

        //TODO: RepositoryInvitation differs from this: https://docs.github.com/en/rest/collaborators/invitations?apiVersion=2022-11-28#update-a-repository-invitation
        // need to add a new data class perhaps

        return null
    }

    /**
     * Delete a repository invitation
     *
     * @param owner String
     * @param repo String
     * @param invitationId Long
     * @see <a href="https://docs.github.com/en/rest/reference/repos#delete-a-repository-invitation">Delete a repository invitation</a>
     * @since 0.1.0
     * @return Boolean True if successful, false if error
     */
    suspend fun deleteRepositoryInvitiation(owner: String, repo: String, invitationId: Long): Boolean {
        try {
            val request = client.delete("https://api.github.com/repos/$owner/$repo/invitations/$invitationId") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.status.value

            return request == 204
        } catch (e: Exception) {
            logger.error("Error deleting repository invitation.", e)
        }

        return false
    }

    /**
     * List repository invitations for the authenticated user
     *
     * @param owner String
     * @param perPage Int
     * @param page Int
     * @see <a href="https://docs.github.com/en/rest/reference/repos#list-repository-invitations-for-the-authenticated-user">List repository invitations for the authenticated user</a>
     * @since 0.1.0
     * @return List<RepositoryInvitation>? List of invitations, null if error
     */
    suspend fun listRepositoryInvitationsForAuthenticatedUser(owner: String, perPage: Int? = 30, page: Int? = 1):
            List<RepositoryInvitation>? {
        try {
            val response = client.get("https://api.github.com/repos/$owner/repository_invitations") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                parameter("per_page", perPage)
                parameter("page", page)
            }

            if (response.status.isSuccess())
                return response.body<List<RepositoryInvitation>>()
        } catch (e: Exception) {
            logger.error("Error getting list of invitations for authenticated user.", e)
        }

        return null
    }

    /**
     * Accept a repository invitation
     *
     * @param owner String
     * @param invitationId Long
     * @see <a href="https://docs.github.com/en/rest/reference/repos#accept-a-repository-invitation">Accept a repository invitation</a>
     * @since 0.1.0
     * @return Boolean True if successful, false if error
     */
    suspend fun acceptRepositoryInvitation(owner: String, invitationId: Long): Boolean {
        try {
            val request = client.patch("https://api.github.com/repos/$owner/repository_invitations/$invitationId") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.status.value

            return request == 204
        } catch (e: Exception) {
            logger.error("Error accepting repository invitation.", e)
        }

        return false
    }

    /**
     * Decline a repository invitation
     *
     * @param owner String
     * @param invitationId Long
     * @see <a href="https://docs.github.com/en/rest/reference/repos#decline-a-repository-invitation">Decline a repository invitation</a>
     * @since 0.1.0
     * @return Boolean True if successful, false if error
     */
    suspend fun declineRepositoryInvitation(owner: String, invitationId: Long): Boolean {
        try {
            val request = client.delete("https://api.github.com/repos/$owner/repository_invitations/$invitationId") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }.status.value

            return request == 204
        } catch (e: Exception) {
            logger.error("Error accepting repository invitation.", e)
        }

        return false
    }

}