plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.smartsecurity.aipipeline"
    compileSdk = 35

    defaultConfig {
        minSdk = 33
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // TODO: Gemini Nano dependencies
}
