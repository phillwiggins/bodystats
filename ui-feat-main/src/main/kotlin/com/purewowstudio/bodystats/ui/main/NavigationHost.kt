package com.purewowstudio.bodystats.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.purewowstudio.bodystats.ui.navigation.NavRoutes
import com.purewowstudio.bodystats.ui.overview.main.OverviewScreen
import com.purewowstudio.bodystats.ui.profile.ProfileScreen

@Composable
fun NavigationHost(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = NavRoutes.Overview.route,
    ) {
        composable(NavRoutes.Overview.route) {
            OverviewScreen()
        }

        composable(NavRoutes.Profile.route) {
            ProfileScreen()
        }
    }
}