package com.smartsecurity.aipipeline

import javax.inject.Inject

class FrameAnalyzer @Inject constructor() {
    fun analyze(byteArray: ByteArray): Float {
        // TODO run Gemini Nano inference and return anomaly score
        return 0f
    }
}
