package com.purewowstudio.bodystats.ui.navigation.di

import com.purewowstudio.bodystats.ui.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavModule {

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()
}