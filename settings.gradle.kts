pluginManagement {
    plugins {
        id("com.android.application") version "8.1.2"
        id("com.android.library") version "8.1.2"
        id("org.jetbrains.kotlin.android") version "1.9.10"
        id("org.jetbrains.kotlin.jvm") version "1.9.10"
        id("org.jetbrains.kotlin.kapt") version "1.9.10"
        id("org.jetbrains.kotlin.plugin.compose") version "1.9.10"
        id("com.google.dagger.hilt.android") version "2.48"
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
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
