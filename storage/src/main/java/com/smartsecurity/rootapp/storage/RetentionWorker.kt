package com.smartsecurity.rootapp.storage

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Periodically purges frames older than 48h.
 */
@HiltWorker
class RetentionWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val dao: FrameDao
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val threshold = System.currentTimeMillis() - 48L * 60 * 60 * 1000
        dao.purgeOlderThan(threshold)
        return Result.success()
    }
}
