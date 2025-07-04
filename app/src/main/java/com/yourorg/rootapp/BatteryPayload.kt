package com.yourorg.rootapp

import java.time.Instant

data class BatteryPayload(
    val deviceId: String,
    val tempC: Double,
    val health: String,
    val ts: Instant
)
