plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.smartsecurity.cloudsync"
    compileSdk = 35

    defaultConfig {
        minSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":network"))
    implementation("androidx.work:work-runtime-ktx:2.8.1")
}
