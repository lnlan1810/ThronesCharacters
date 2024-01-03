@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Game Characters"
include(":app")
include(":core:base")
include(":core:di")
include(":core:extensions")
include(":core:navigation")
include(":core:network")
include(":core:designsystem")

include(":feature:home")
include(":feature:find")
include(":feature:profile")
include(":feature:setting")


 