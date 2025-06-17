package com.smartsecurity.tests

import android.content.Context
import android.os.BatteryManager
import androidx.test.core.app.ApplicationProvider
import com.smartsecurity.edge.CpuTempReader
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowBatteryManager

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class CpuTempReaderTest {
    @Test
    fun readBatteryTemp_returnsValue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val shadowBm = shadowOf(bm) as ShadowBatteryManager
        shadowBm.setIntProperty(BatteryManager.BATTERY_PROPERTY_TEMPERATURE, 370)

        val temp = CpuTempReader.readBatteryTempC(context)
        assertNotNull(temp)
    }

    @Test
    fun readCpuTemp_doesNotThrow() {
        CpuTempReader.readCpuTempC()
    }
}
