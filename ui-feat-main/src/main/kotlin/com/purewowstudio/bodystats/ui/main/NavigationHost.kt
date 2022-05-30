package com.purewowstudio.bodystats.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.purewowstudio.bodystats.ui.navigation.NavRoutes
import com.purewowstudio.bodystats.ui.overview.OverviewScreen
import com.purewowstudio.bodystats.ui.profile.ProfileScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
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