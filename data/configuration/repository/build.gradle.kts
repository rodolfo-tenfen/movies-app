plugins {
    id(libs.plugins.jetbrainsKotlinJvm.get().pluginId)
    alias(libs.plugins.ktlint.gradle)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":data:configuration"))
    implementation(project(":data:tmdb"))
    implementation(project(":domain"))
    implementation(project(":domain:configuration"))
}
