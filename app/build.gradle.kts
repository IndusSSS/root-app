plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.smartsecurity.rootapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.smartsecurity.rootapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0.1-alpha"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("Boolean", "LOG_CPU_TEMP", "true")
        buildConfigField("Boolean", "LOG_IMU", "true")
        buildConfigField("Boolean", "STREAM_VIDEO", "true")
        buildConfigField("Boolean", "OFFLINE_CACHE", "true")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
        create("rootRelease") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.7.0"
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":telemetry"))
    implementation(project(":stream"))
    implementation(project(":storage"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui:1.7.0")
    implementation("androidx.compose.material3:material3:1.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.5")

    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
}
