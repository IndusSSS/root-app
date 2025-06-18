package com.smartsecurity.rootapp.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Persistent sensor frame for offline caching.
 */
@Entity(tableName = "frames")
data class FrameEntity(
    @PrimaryKey val ts: Long,
    val data: ByteArray
)
