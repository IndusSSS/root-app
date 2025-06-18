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

rootProject.name = "root-app"
include(
    ":app",
    ":core",
    ":telemetry",
    ":stream",
    ":storage",
    ":common",
    ":device-core",
    ":ai-pipeline",
    ":network",
    ":ui",
    ":embedded-server",
    ":cloud-sync",
    ":tests"
)
