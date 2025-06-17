package com.smartsecurity.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

object ApiClient {
    val httpClient = HttpClient(CIO) {
        // TODO configure TLS pinning
    }
}
