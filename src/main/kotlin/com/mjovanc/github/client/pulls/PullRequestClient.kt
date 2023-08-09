package com.mjovanc.github.client.pulls

import com.mjovanc.github.client.collaborators.CollaboratorsClient
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

    // List pull requests

    // Create a pull request

    // Get a pull request

    // Update a pull request

    // List commits on a pull request

    // List pull requests files

    // Check if a pull request has been merged

    // Merge a pull request

    // Update a pull request branch

}