package com.smartsecurity.rootapp.core

/**
 * Compile-time feature flags configured via BuildConfig.
 */
object FeatureFlags {
    val LOG_CPU_TEMP: Boolean = BuildConfig.LOG_CPU_TEMP
    val LOG_IMU: Boolean = BuildConfig.LOG_IMU
    val STREAM_VIDEO: Boolean = BuildConfig.STREAM_VIDEO
    val OFFLINE_CACHE: Boolean = BuildConfig.OFFLINE_CACHE
}
