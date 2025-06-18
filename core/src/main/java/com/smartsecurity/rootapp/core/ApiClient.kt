package com.smartsecurity.rootapp.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 * Provides a configured [Retrofit] instance for network calls.
 */
object ApiClient {
    private val json = Json { ignoreUnknownKeys = true }

    private val httpClient: OkHttpClient = OkHttpClient.Builder().build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://cloud.smartsecurity.solutions/api/v2/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(httpClient)
        .build()
}
