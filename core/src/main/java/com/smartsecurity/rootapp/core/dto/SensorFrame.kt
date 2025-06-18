package com.smartsecurity.rootapp.core.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data frame captured from device sensors.
 */
@Serializable
data class SensorFrame(
    @SerialName("ts") val timestamp: Long,
    @SerialName("cpu") val cpuTemp: Float,
    @SerialName("bat") val battery: Int,
    @SerialName("ax") val accel: List<Float>,
    @SerialName("gyro") val gyro: List<Float>,
    @SerialName("rssi") val rssi: Int
)
