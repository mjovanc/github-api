package com.mjovanc.github.client.organizations

import com.mjovanc.github.client.collaborators.CollaboratorsClient
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Organizations Client
 *
 * Implements the GitHub API endpoints seen in
 * https://docs.github.com/en/rest/orgs/orgs
 *
 * @author Marcus Cvjeticanin
 * @since 0.1.0
 */
class OrganizationsClient {

    private val logger = LoggerFactory.getLogger("OrganizationsClient")

    private val properties = Properties()

    private var token = System.getenv("GITHUB_TOKEN")

    init {
        if (token == null) {
            properties.load(CollaboratorsClient::class.java.getResourceAsStream("/github.properties"))
            token = properties.getProperty("token")
        }
    }

    // List organizations

    // Get an organization

    // Update an organization

    // Delete an organization

    // List app installations for an organization

    // Enable or disable a security feature for an organization

    // List organizations for the authenticated user

    // List organizations for a user

}