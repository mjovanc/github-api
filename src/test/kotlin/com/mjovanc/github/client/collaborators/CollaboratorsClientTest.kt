package com.mjovanc.github.client.collaborators

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class CollaboratorsClientTest {

    private val client = CollaboratorsClient()

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can list repository collaborators`() = runTest {
        // Arrange
        val owner = "mjovanc"
        val repo = "github-api"

        // Act
        val data = client.listRepositoryCollaborators(owner=owner, repo=repo)

        // Assert
        assertNotNull(data)
    }
}