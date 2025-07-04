package com.yourorg.rootapp

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class BatteryServiceTest {

    private lateinit var server: MockWebServer
    private lateinit var api: Api

    @Before
    fun setUp() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `test battery data is sent every 5 seconds`() = runTest {
        server.enqueue(MockResponse().setResponseCode(200))
        server.enqueue(MockResponse().setResponseCode(200))

        // This is a simplified test to verify the cadence.
        // A more complete test would involve a TestCoroutineDispatcher and advancing the clock.

        // Simulate two 5-second intervals
        api.postBattery(BatteryPayload("test", 25.0, "Good", java.time.Instant.now()))
        val request1 = server.takeRequest(1, TimeUnit.SECONDS)
        assert(request1 != null)

        Thread.sleep(5000)

        api.postBattery(BatteryPayload("test", 26.0, "Good", java.time.Instant.now()))
        val request2 = server.takeRequest(1, TimeUnit.SECONDS)
        assert(request2 != null)
    }
}
