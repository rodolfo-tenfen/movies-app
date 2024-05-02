pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Movies App"
include(":app")

include(":data:configuration")
include(":data:configuration:repository")
include(":data:movie")
include(":data:movie:repository")
include(":data:tmdb")

include(":domain")
include(":domain:configuration")
include(":domain:movie")

include(":theme")

include(":feature:home")
include(":feature:movieitem")
