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
    implementation(project(":data:configuration"))
    implementation(project(":data:movie"))

    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp3.logging.interceptor)
}
