package com.purewowstudio.bodystats.data.healthconnect.di

import com.purewowstudio.bodystats.data.healthconnect.main.HealthConnectManager
import com.purewowstudio.bodystats.domain.healthdata.HealthData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HealthConnectModule {

    @Singleton
    @Binds
    internal abstract fun bindHealthData(
        healthConnectManager: HealthConnectManager
    ): HealthData
}