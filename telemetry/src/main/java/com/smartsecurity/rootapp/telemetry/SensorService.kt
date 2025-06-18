package com.smartsecurity.rootapp.telemetry

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.smartsecurity.rootapp.core.dto.SensorFrame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Foreground service collecting and buffering sensor data.
 */
class SensorService : Service() {

    private val scope = CoroutineScope(Dispatchers.Default)
    private var job: Job? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, NotificationCompat.Builder(this, "telemetry")
            .setContentTitle("Telemetry")
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .build())
        job = scope.launch { collectLoop() }
        return START_STICKY
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    private suspend fun collectLoop() {
        // TODO collect sensor data and buffer via WebSocket
    }
}
