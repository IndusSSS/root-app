plugins {
    id("com.android.library")
    kotlin("android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.smartsecurity.rootapp.stream"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core"))
    implementation("androidx.camera:camera-camera2:1.5.3")
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
}
