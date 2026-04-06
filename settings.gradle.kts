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

include(":data:tmdb:configuration:datasource")
include(":data:tmdb:movie:datasource")
include(":data:tmdb:movie:repository")
include(":data:tmdb")

include(":domain:movie")

include(":theme")

include(":previewdata")

include(":feature:home")

include(":feature:movie:model")
include(":feature:movie:ui")
include(":feature:movie:ui:item")
