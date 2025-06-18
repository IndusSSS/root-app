package com.smartsecurity.rootapp.storage

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room database holding [FrameEntity] items.
 */
@Database(entities = [FrameEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun frameDao(): FrameDao
}
