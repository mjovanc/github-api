package com.mjovanc.github.client.collaborators

import kotlinx.coroutines.test.runTest
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

}