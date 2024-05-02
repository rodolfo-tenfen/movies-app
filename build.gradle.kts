plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ktlint.gradle)
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
}

ktlint {
    android.set(true)
    outputColorName.set("RED")

    filter {
        exclude { element ->
            element.file.path.contains("generated")
        }
    }
}
