import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

kotlin {
    // Do not enable explicit api for cli project
    if (project.name != "ktlint") {
        explicitApiWarning()
    }
}

addAdditionalJdkVersionTests()
