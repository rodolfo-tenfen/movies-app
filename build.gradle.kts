plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ktlint.gradle)
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
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
