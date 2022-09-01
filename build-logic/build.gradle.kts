plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

// Pass '-PkotlinDev' to command line to enable kotlin-in-development version
val kotlinVersion = if (project.hasProperty("kotlinDev")) {
    logger.warn("Enabling kotlin dev version!")
    libs.versions.kotlinDev.get()
} else {
    libs.versions.kotlin.get() // Keep in sync with 'gradle/libs.versions.toml and check whether dokka-gradle-plugin has been updated as well
}

dependencies {
    implementation(libs.kotlin.plugin)
    implementation(libs.dokka)
}
