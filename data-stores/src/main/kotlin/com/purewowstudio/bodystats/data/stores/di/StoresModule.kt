package com.purewowstudio.bodystats.data.stores.di

import com.purewowstudio.bodystats.data.stores.PrefsNutritionGoalStore
import com.purewowstudio.bodystats.data.stores.PrefsUserStore
import com.purewowstudio.bodystats.domain.stores.NutritionGoalStore
import com.purewowstudio.bodystats.domain.stores.UserStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StoresModule {

    @Binds
    internal abstract fun bindUserStore(
        userStore: PrefsUserStore
    ): UserStore

    @Binds
    internal abstract fun bindNutritionGoalStore(
        nutritionGoalStore: PrefsNutritionGoalStore
    ): NutritionGoalStore
}