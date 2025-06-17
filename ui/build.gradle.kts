plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.smartsecurity.ui"
    compileSdk = 35

    defaultConfig {
        minSdk = 33
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3:1.2.0")
}
