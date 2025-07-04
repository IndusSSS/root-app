package com.yourorg.rootapp

import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("api/v1/battery")
    suspend fun postBattery(@Body payload: BatteryPayload)
}
