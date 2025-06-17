package com.smartsecurity.device.core

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CameraRepository @Inject constructor(@ApplicationContext private val context: Context) {
    suspend fun startPreview() = withContext(Dispatchers.Main) {
        // TODO start CameraX preview
        val provider = ProcessCameraProvider.getInstance(context).get()
        val preview = Preview.Builder().build()
        provider.bindToLifecycle(/* lifecycleOwner */null, CameraSelector.DEFAULT_BACK_CAMERA, preview)
    }

    suspend fun startRecording() = withContext(Dispatchers.IO) {
        // TODO record MP4 and HLS into Room
    }
}
