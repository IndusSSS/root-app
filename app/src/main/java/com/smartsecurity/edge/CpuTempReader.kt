package com.smartsecurity.edge

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Utility for reading device temperature without requiring privileged permissions.
 */
object CpuTempReader {

    /**
     * Reads CPU temperature in Celsius or null if unavailable.
     */
    fun readCpuTempC(): Float? = runBlocking {
        scanThermalZones()
    }

    /**
     * Reads battery temperature via [BatteryManager] fallback.
     */
    fun readBatteryTempC(context: Context): Float? {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as? BatteryManager
        val fromProp = bm?.getIntProperty(BatteryManager.BATTERY_PROPERTY_TEMPERATURE)
        if (fromProp != null && fromProp != Int.MIN_VALUE) {
            val temp = fromProp / 10f
            if (temp in -40f..200f) return temp
        }
        val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val extra = intent?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, Int.MIN_VALUE)
        if (extra != null && extra != Int.MIN_VALUE) {
            val temp = extra / 10f
            if (temp in -40f..200f) return temp
        }
        return null
    }

    private suspend fun scanThermalZones(): Float? = withContext(Dispatchers.IO) {
        val roots = listOf(
            File("/sys/class/thermal"),
            File("/sys/devices/virtual/thermal")
        )
        for (root in roots) {
            if (!root.isDirectory) continue
            root.listFiles { file -> file.name.startsWith("thermal_zone") }?.forEach { zone ->
                val typeFile = File(zone, "type")
                val type = runCatching { typeFile.readText().trim() }.getOrNull()?.lowercase()
                if (type != null && ("cpu" in type || "soc" in type)) {
                    val tempFile = File(zone, "temp")
                    val raw = runCatching { tempFile.readText().trim() }.getOrNull()
                    val value = raw?.toFloatOrNull() ?: return@forEach
                    val c = if (value > 150) value / 1000f else value
                    if (c in -40f..200f) return@withContext c
                }
            }
        }
        null
    }
}
