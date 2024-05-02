plugins {
    id(libs.plugins.jetbrainsKotlinJvm.get().pluginId)
    alias(libs.plugins.ktlint.gradle)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
