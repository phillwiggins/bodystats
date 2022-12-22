package com.purewowstudio.bodystats.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.purewowstudio.bodystats.ui.navigation.NavRoute
import com.purewowstudio.bodystats.ui.navigation.NavigationManager
import com.purewowstudio.bodystats.ui.overview.main.OverviewScreen
import com.purewowstudio.bodystats.ui.profile.ProfileScreen
import com.purewowstudio.bodystats.ui.sleep.SleepScreen
import com.purewowstudio.bodystats.ui.weight.WeightScreen

@Composable
fun NavigationHost(
    paddingValues: PaddingValues,
    navController: NavHostController,
    onDetailViewDisplayed: (Boolean) -> Unit,
    setNavName: (String?) -> Unit,
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = NavRoute.Overview.route,
    ) {

        composable(NavRoute.Overview.route) {
            setNavName(NavRoute.Overview.title)
            onDetailViewDisplayed(false)
            OverviewScreen()
        }

        composable(NavRoute.Profile.route) {
            setNavName(NavRoute.Profile.title)
            onDetailViewDisplayed(false)
            ProfileScreen()
        }

        composable(NavRoute.Sleep.route) {
            setNavName(NavRoute.Sleep.title)
            onDetailViewDisplayed(true)
            SleepScreen()
        }

        composable(NavRoute.Weight.route) {
            setNavName(NavRoute.Weight.title)
            onDetailViewDisplayed(true)
            WeightScreen()
        }
    }
}