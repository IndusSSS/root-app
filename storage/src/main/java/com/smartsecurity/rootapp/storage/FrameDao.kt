package com.smartsecurity.rootapp.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO for sensor frame storage.
 */
@Dao
interface FrameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FrameEntity)

    @Query("DELETE FROM frames WHERE ts < :threshold")
    suspend fun purgeOlderThan(threshold: Long)
}
