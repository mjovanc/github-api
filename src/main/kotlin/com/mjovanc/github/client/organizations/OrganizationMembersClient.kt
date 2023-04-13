package com.mjovanc.github.client.organizations

import com.mjovanc.github.client.collaborators.CollaboratorsClient
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

}