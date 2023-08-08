package com.mjovanc.github.client.organizations

import com.mjovanc.github.client.client
import com.mjovanc.github.client.collaborators.CollaboratorsClient
import com.mjovanc.github.model.response.collaborators.RepositoryInvitation
import com.mjovanc.github.model.response.organizations.OrganizationInvitation
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Organization Members Client
 *
 * Implements the GitHub API endpoints seen in
 * https://docs.github.com/en/rest/orgs/members
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
class OrganizationMembersClient {

    private val logger = LoggerFactory.getLogger("OrganizationMembersClient")

    private val properties = Properties()

    private var token = System.getenv("GITHUB_TOKEN")

    init {
        if (token == null) {
            properties.load(CollaboratorsClient::class.java.getResourceAsStream("/github.properties"))
            token = properties.getProperty("token")
        }
    }


    /**
     * List failed organization invitations
     *
     * @param org The organization name
     * @param repo The repository
     * @param perPage Number of invitations per page
     * @param page The current page
     * @see <a href="https://docs.github.com/en/rest/orgs/members?apiVersion=2022-11-28#list-failed-organization-invitations">List failed organization invitations</a>
     * @since 0.1.0
     * @return List<OrganizationInvitation>? List of failed invitations, null if error
     */
    suspend fun listFailedOrganizationInvitations(org: String, perPage: Int? = 30, page: Int? = 1):
            List<OrganizationInvitation>? {
        try {
            return client.get("https://api.github.com/orgs/$org/invitations") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                parameter("per_page", perPage)
                parameter("page", page)
            }.body<List<OrganizationInvitation>>()
        } catch (e: Exception) {
            logger.error("Error getting list of failed organization invitations.", e)
        }

        return null
    }

    /**
     * List pending organization invitations
     *
     * @param org The organization name
     * @param repo The repository
     * @param perPage Number of invitations per page
     * @param page The current page
     * @see <a href="https://docs.github.com/en/rest/orgs/members?apiVersion=2022-11-28#list-failed-organization-invitations">List failed organization invitations</a>
     * @since 0.1.0
     * @return List<OrganizationInvitation>? List of invitations, null if error
     */
    suspend fun listPendingOrganizationInvitations(org: String, perPage: Int? = 30, page: Int? = 1, role: String? = "all", invitationSource: String? = "all"):
            List<OrganizationInvitation>? {
        try {
            return client.get("https://api.github.com/orgs/$org/failed_invitations") {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
                parameter("per_page", perPage)
                parameter("page", page)
                parameter("role", role)
                parameter("invitation_source", invitationSource)
            }.body<List<OrganizationInvitation>>()
        } catch (e: Exception) {
            logger.error("Error getting list of pending organization invitations.", e)
        }

        return null
    }

    // Create an organization invitation

    // Cancel an organization invitation

    // List organization invitation teams

    // List organization members

    // Check organization membership for a user

    // Remove an organization member

    // Get organization membership for a user

    // Set organization membership for a user

    // Remove organization membership for a user

    // List public organization members

    // Check public organization membership for a user

    // Set public organization membership for the authenticated user

    // Remove public organization membership for the authenticated user

    // List organization memberships for the authenticated user

    // Get an organization membership for the authenticated user

    // Update an organization membership for the authenticated user

}