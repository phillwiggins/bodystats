package com.purewowstudio.bodystats.data.healthconnect.di

import com.purewowstudio.bodystats.data.healthconnect.data.HealthDataCaloriesImpl
import com.purewowstudio.bodystats.data.healthconnect.data.HealthDataSleepImpl
import com.purewowstudio.bodystats.data.healthconnect.data.HealthDataStepsImpl
import com.purewowstudio.bodystats.data.healthconnect.data.HealthDataWeightImpl
import com.purewowstudio.bodystats.domain.healthdata.HealthDataCalories
import com.purewowstudio.bodystats.domain.healthdata.HealthDataSleep
import com.purewowstudio.bodystats.domain.healthdata.HealthDataSteps
import com.purewowstudio.bodystats.domain.healthdata.HealthDataWeight
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
abstract class HealthConnectDataModule {

    @Binds
    internal abstract fun bindSleepData(
        healthConnectSleepImpl: HealthDataSleepImpl
    ): HealthDataSleep

    @Binds
    internal abstract fun bindWeightData(
        healthConnectWeightImpl: HealthDataWeightImpl
    ): HealthDataWeight

    @Binds
    internal abstract fun bindCaloriesData(
        healthConnectCaloriesImpl: HealthDataCaloriesImpl
    ): HealthDataCalories

    @Binds
    internal abstract fun bindStepsData(
        healthDataStepsImpl: HealthDataStepsImpl
    ): HealthDataSteps
}