package com.smartsecurity.edge.di

import android.content.Context
import com.smartsecurity.device.core.CameraRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCameraRepository(@ApplicationContext context: Context): CameraRepository {
        return CameraRepository(context)
    }
}
