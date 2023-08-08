val ktor_version: String by project
val coroutines_version: String by project
val slf4j_version: String by project
val kotlin_version: String by project
val ossrhUsername: String? = System.getProperty("ossrhUsername")
val ossrhPassword: String? = System.getProperty("ossrhPassword") // this file should be in the HOME directory gradle.properties

plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("org.jetbrains.dokka") version "1.8.10"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
    `java-library`
    `maven-publish`
    signing
    application
}

group = "com.mjovanc.github"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

java {
    withJavadocJar()
    withSourcesJar()
}

koverReport {
    filters {
        excludes {
            classes("com.mjovanc.github.model.*")
        }
    }

    verify {
        onCheck = true
        rule {
            isEnabled = true
            entity = kotlinx.kover.gradle.plugin.dsl.GroupingEntityType.APPLICATION

            bound {
                minValue = 60
                maxValue = 90
                metric = kotlinx.kover.gradle.plugin.dsl.MetricType.LINE
                aggregation = kotlinx.kover.gradle.plugin.dsl.AggregationType.COVERED_PERCENTAGE
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "github-api"
            groupId = "com.mjovanc.github"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("Kotlin API for GitHub")
                description.set("Kotlin API for GitHub")
                url.set("https://github.com/mjovanc/github-api")
                licenses {
                    license {
                        name.set("The 3-Clause BSD License")
                        url.set("https://github.com/mjovanc/github-api/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("mjovanc")
                        name.set("Marcus Cvjeticanin")
                        email.set("mjovanc@icloud.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:mjovanc/github-api.git")
                    developerConnection.set("scm:git@github.com:mjovanc/github-api.git")
                    url.set("https://github.com/mjovanc/github-api")
                }
            }
        }
    }
    repositories {
        maven {
            name = "Sonatype"
            val host = "https://s01.oss.sonatype.org"
            val path = if (version.toString().endsWith("SNAPSHOT")) "/content/repositories/snapshots/"
            else "/service/local/staging/deploy/maven2/"
            url = uri(host.plus(path))
            println("> publish.url: $url")
            println("> publish.path: $path")
            println("> publish.version: $version")

            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

tasks.register<Copy>("copyDokkaHtmlMultiModule") {
    dependsOn("dokkaHtmlMultiModule")
    println("${projectDir}/docs/${version}")
    mkdir("${projectDir}/docs/${version}")

    from("${buildDir}/dokka/htmlMultiModule")
    into(layout.buildDirectory.dir("${projectDir}/docs/${version}"))
    include("**/*.*")
}