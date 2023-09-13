package com.mjovanc.github.client.pulls

import com.mjovanc.github.model.request.pulls.CreatePullRequest
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class PullRequestClientTest {

    private val client = PullRequestClient()

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can list pull requests`() = runTest {
        val owner = "mjovanc"
        val repo = "github-api"

        val data = client.listPullRequests(
            owner=owner,
            repo=repo
        )

        assertNotNull(data)
    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can create pull request`() = runTest {
        val owner = "mjovanc"
        val repo = "github-api"

        val pullRequest = CreatePullRequest(
            title="Test PR (UNIT TEST)",
            body="Please pull these amazing changes in!",
            head="github-api:test-pr",
            base="master"
        )

        val data = client.createPullRequest(
            owner=owner,
            repo=repo,
            pullRequest=pullRequest
        )

        assertNotNull(data)
    }

    /*@Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can get a pull request`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can update a pull request`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can list commits on a pull request`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can list pull request files`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can check if a pull request has been merged`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can merge a pull request`() = runTest {

    }

    @Test
    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    fun `can update a pull request branch`() = runTest {

    }*/
}