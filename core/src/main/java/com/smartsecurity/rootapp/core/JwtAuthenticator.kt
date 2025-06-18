package com.smartsecurity.rootapp.core

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Attaches a bearer token to every request.
 */
class JwtAuthenticator(private val tokenProvider: () -> String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider()
        val request = if (token != null) {
            chain.request().newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}
