plugins {
    id(libs.plugins.jetbrainsKotlinJvm.get().pluginId)
    alias(libs.plugins.ktlint.gradle)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":domain:configuration"))
    implementation(project(":domain:movie"))

    implementation(libs.moshi)
    ksp(libs.moshi.kotlin.codegen)
}
