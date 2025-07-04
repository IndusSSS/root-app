package com.yourorg.rootapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.BatteryManager
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Instant

class BatteryService : LifecycleService() {

    private lateinit var api: Api

    override fun onCreate() {
        super.onCreate()
        setupRetrofit()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startForeground(1, createNotification())

        lifecycleScope.launch {
            while (true) {
                collectAndSendBatteryData()
                delay(5000)
            }
        }

        return START_STICKY
    }

    private fun setupRetrofit() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                var response = chain.proceed(chain.request())
                var tryCount = 0
                while (!response.isSuccessful && tryCount < 3) {
                    tryCount++
                    response.close()
                    Thread.sleep(1000L * tryCount)
                    response = chain.proceed(chain.request())
                }
                response
            }
            .build()

        api = Retrofit.Builder()
            .baseUrl("https://cloud.smartsecurity.solutions/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    private suspend fun collectAndSendBatteryData() {
        val batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
        val tempDeci = batteryManager.getLongProperty(BatteryManager.BATTERY_PROPERTY_TEMPERATURE)
        val tempC = tempDeci / 10.0

        val batteryIntent = registerReceiver(null, Intent.Filter(Intent.ACTION_BATTERY_CHANGED))
        val health = when (batteryIntent?.getIntExtra(BatteryManager.EXTRA_HEALTH, 0)) {
            BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
            BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over Voltage"
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Failure"
            else -> "Unknown"
        }

        val deviceId = "Samsung_M05" // Replace with a unique device identifier
        val payload = BatteryPayload(deviceId, tempC, health, Instant.now())

        try {
            api.postBattery(payload)
        } catch (e: Exception) {
            // Handle exceptions
        }
    }

    private fun createNotification(): Notification {
        val channel = NotificationChannel(
            "battery_service_channel",
            "Battery Telemetry",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        return Notification.Builder(this, "battery_service_channel")
            .setContentTitle("Telemetry Streaming")
            .setContentText("Sending battery data to the cloud.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}
