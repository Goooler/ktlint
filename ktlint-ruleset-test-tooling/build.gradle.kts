plugins {
    id("ktlint-kotlin-common")
//    id("ktlint-publication") No need to publish? If so, also remove gradle.properties
}

dependencies {
    api(projects.ktlintRulesetCore)
}
