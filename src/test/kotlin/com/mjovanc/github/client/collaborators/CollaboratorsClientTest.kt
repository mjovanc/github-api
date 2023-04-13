package com.mjovanc.github.client.collaborators

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CollaboratorsClientTest {

    private val client = CollaboratorsClient()

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can list repository collaborators`() = runTest {
        val owner = "mjovanc"
        val repo = "github-api"

        val dataWithOutQueryParams = client.listRepositoryCollaborators(owner=owner, repo=repo)
        val dataWithQueryParams = client.listRepositoryCollaborators(owner=owner, repo=repo, affiliation="direct")

        assertNotNull(dataWithOutQueryParams)
        assertNotNull(dataWithQueryParams)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can check if user is a repository collaborator`() = runTest {
        val shouldBeACollaborator = client.checkIfUserIsARepositoryCollaborator(owner="mjovanc", repo="github-api", username="mjovanc")
        val shouldNotBeACollaborator = client.checkIfUserIsARepositoryCollaborator(owner="mjovanc", repo="github-api", username="mjovanc2")

        assertTrue { shouldBeACollaborator!! }
        assertFalse { shouldNotBeACollaborator!! }
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun addRepositoryCollaborator() {
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun removeRepositoryCollaborator() {
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun getRepositoryPermissionForUser() {
    }
}