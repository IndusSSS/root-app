package com.smartsecurity.cloudsync

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LogUploader(private val client: HttpClient) {
    suspend fun uploadLogs(entries: List<String>, token: String) {
        client.post("https://cloud.smartsecurity.solutions/api/v2/ingest") {
            header("X-EDGE-TOKEN", token)
            contentType(ContentType.Application.Json)
            setBody(entries)
        }
    }
}
