plugins {
    id(libs.plugins.kotlin.jvm.get().pluginId)

    alias(libs.plugins.ktlint.gradle)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(libs.versions.jvmTarget.get().toInt())
}
