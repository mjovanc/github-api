# Kotlin API for GitHub 

[![alpha](https://kotl.in/badges/alpha.svg)](https://kotlinlang.org/docs/components-stability.html)
[![build](https://img.shields.io/github/actions/workflow/status/mjovanc/github-api/master-ci.yml?branch=master)](https://github.com/mjovanc/github-api/actions/workflows/master-ci.yml) 
[![kotlin](https://img.shields.io/badge/kotlin-1.8.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![license](https://img.shields.io/badge/License-BSD_3--Clause-blue.svg)](https://opensource.org/licenses/BSD-3-Clause)
[![codecov](https://codecov.io/gh/mjovanc/github-api/branch/master/graph/badge.svg)](https://codecov.io/gh/mjovanc/github-api)

Kotlin API for GitHub.

## Getting Help

Are you having trouble with Kotlin API for GitHub? We want to help!

- If you are upgrading, read the release notes for upgrade instructions and "new and noteworthy" features.

- Ask a question we monitor stackoverflow.com for questions tagged with kotlin-github-api.

- Report bugs with Kotlin API for GitHub at https://github.com/mjovanc/github-api/issues.

## Reporting Issues

Kotlin API for GitHub uses GitHub’s integrated issue tracking system to record bugs and feature requests. If you want to raise an issue, please follow the recommendations below:

- Before you log a bug, please search the issue tracker to see if someone has already reported the problem.

- If the issue doesn’t already exist, create a new issue.

- Please provide as much information as possible with the issue report. We like to know the Kotlin API for GitHub version, operating system, and JVM version you’re using.

- If you need to paste code or include a stack trace, use Markdown. ``` escapes before and after your text.

- If possible, try to create a test case or project that replicates the problem and attach it to the issue.

## Get started

To install Kotlin API for GitHub into your Gradle project we need to include the dependency:

**Gradle**
```gradle
dependencies {
    implementation 'com.mjovanc.github:api:<version>'
}
```

**Gradle Kotlin DSL**
```kotlin
dependencies {
    implementation("com.mjovanc.github:api:<version>")
}
```

## Usage

Then simply use a client depending on your need. In this example, to use CollaboratorsClient 
you need to have a valid GitHub token and add it either to a file called `github.properties` in the root of the project
or as an environment variable called `GITHUB_TOKEN`. 

To use the client, simply create an instance of it and call the methods you need.

```kotlin
fun main() = runBlocking {
    val client = CollaboratorsClient()
    val collaborators: List<Collaborator> = client.listCollaborators("mjovanc", "github-api")
    collaborators.forEach { println(it.login) }
}
```


## Contributors

The following contributors have either helped to start this project, have contributed
code, are actively maintaining it (including documentation), or in other ways
being awesome contributors to this project. **We'd like to take a moment to recognize them.**

[<img src="https://github.com/mjovanc.png?size=72" alt="mjovanc" width="72">](https://github.com/mjovanc)
[<img src="https://github.com/renovatebot.png?size=72" alt="mjovanc" width="72">](https://github.com/renovatebot)

## License

The 3-Clause BSD License.
