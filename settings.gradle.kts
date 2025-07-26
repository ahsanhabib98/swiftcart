pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()  // for loading-button & stepview
        maven { url = uri("https://verve.jfrog.io/artifactory/verve-gradle-release/") }  // for viewpagerindicator
        maven { url = uri("https://jitpack.io") }  // optional
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://verve.jfrog.io/artifactory/verve-gradle-release/") }
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Swift"
include(":app")
