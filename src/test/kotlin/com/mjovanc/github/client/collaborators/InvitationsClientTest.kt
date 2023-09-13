package com.mjovanc.github.client.collaborators

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class InvitationsClientTest {

    private val client = InvitationsClient()

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can list repository invitations`() = runTest {
        val owner = "mjovanc"
        val repo = "github-api"

        val data = client.listRepositoryInvitations(owner=owner, repo=repo)

        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can update repository invitation`() = runTest {
        val owner = "mjovanc"
        val repo = "github-api"
        val invitationId = 1L
        val permission = "write"

       /* val data = client.updateRepositoryInvitation(
            owner=owner,
            repo=repo,
            invitationId=invitationId,
            permission=permission
        )

        assertNotNull(data)*/
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can delete repository invitation`() = runTest {
        val owner = "mjovanc"
        val repo = "github-api"
        val invitationId = 1L

        val data = client.deleteRepositoryInvitiation(
            owner=owner,
            repo=repo,
            invitationId=invitationId
        )

        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can list repository invitations for authenticated user`() = runTest {
        //TODO: need to make sure there is a pending repository invitation
        // or if there is a nicer way to solve this. Currently there is none so
        // we assert its null
        val owner = "mjovanc"
        val perPage = 10
        val page = 2

        val data = client.listRepositoryInvitationsForAuthenticatedUser(
            owner=owner,
            perPage=perPage,
            page=page
        )

        assertNull(data)
    }

    /*@Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can accept repository invitation`() = runTest {
    }*/

    /*@Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can decline repository invitation`() = runTest {
    }*/
}