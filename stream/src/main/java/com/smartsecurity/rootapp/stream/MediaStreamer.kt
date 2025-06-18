package com.smartsecurity.rootapp.stream

import android.content.Context
import androidx.camera.lifecycle.ProcessCameraProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Captures camera frames and streams them to the backend.
 */
class MediaStreamer @Inject constructor(@ApplicationContext private val context: Context) {
    suspend fun start() {
        val provider = ProcessCameraProvider.getInstance(context).get()
        // TODO configure MediaCodec and HTTP upload
    }
}
