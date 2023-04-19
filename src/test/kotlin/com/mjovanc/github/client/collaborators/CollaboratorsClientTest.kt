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
        val shouldBeACollaborator = client.checkIfUserIsARepositoryCollaborator(
            owner="mjovanc",
            repo="github-api",
            username="mjovanc"
        )
        val shouldNotBeACollaborator = client.checkIfUserIsARepositoryCollaborator(
            owner="mjovanc",
            repo="github-api",
            username="mjovanc2"
        )

        assertTrue { shouldBeACollaborator!! }
        assertFalse { shouldNotBeACollaborator!! }
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can add repository collaborator`() = runTest {
        val data = client.addRepositoryCollaborator(
            owner="mjovanc",
            repo="github-api",
            username="TechyGuy",
            permission="pull"
        )

        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can remove repository collaborator`() = runTest {
        val data = client.removeRepositoryCollaborator(owner="mjovanc", repo="github-api", username="marcuscvj")

        assertTrue { data!! }
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get repository permission for user`() = runTest {
        val collaboratorPermission = client.getRepositoryPermissionForUser(
            owner="mjovanc",
            repo="github-api",
            username="mjovanc"
        )
        val collaboratorPermission2 = client.getRepositoryPermissionForUser(
            owner="mjovanc",
            repo="github-api",
            username="TechyGuy"
        )

        if (collaboratorPermission != null) { assertTrue { collaboratorPermission.permission == "admin" } }
        if (collaboratorPermission2 != null) { assertTrue { collaboratorPermission2.permission == "read" } }
        if (collaboratorPermission2 != null) { assertFalse { collaboratorPermission2.permission == "admin" } }
    }
}