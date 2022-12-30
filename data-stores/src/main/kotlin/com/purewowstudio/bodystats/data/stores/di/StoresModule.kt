package com.purewowstudio.bodystats.data.stores.di

import com.purewowstudio.bodystats.data.stores.PrefsNutritionGoalStore
import com.purewowstudio.bodystats.data.stores.PrefsUserPrefsStore
import com.purewowstudio.bodystats.data.stores.PrefsUserStore
import com.purewowstudio.bodystats.domain.stores.NutritionGoalStore
import com.purewowstudio.bodystats.domain.stores.UserPrefsStore
import com.purewowstudio.bodystats.domain.stores.UserStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StoresModule {

    @Binds
    @Singleton
    internal abstract fun bindUserStore(
        userStore: PrefsUserStore
    ): UserStore

    @Binds
    @Singleton
    internal abstract fun bindNutritionGoalStore(
        nutritionGoalStore: PrefsNutritionGoalStore
    ): NutritionGoalStore

    @Binds
    @Singleton
    internal abstract fun bindUserPrefsStore(
        userPrefsStore: PrefsUserPrefsStore
    ): UserPrefsStore
}