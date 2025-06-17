pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "pocket-edge"
include(
    ":app",
    ":common",
    ":device-core",
    ":ai-pipeline",
    ":network",
    ":ui",
    ":embedded-server",
    ":cloud-sync",
    ":tests"
)
