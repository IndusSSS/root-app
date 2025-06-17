package com.smartsecurity.embedded.server

import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.routing.routing
import io.ktor.server.http.content.staticFiles
import java.io.File

class EmbeddedServer {
    fun start(root: File) {
        embeddedServer(CIO, port = 8080) {
            routing {
                staticFiles("/", root)
            }
        }.start(wait = false)
    }
}
