package com.purewowstudio.bodystats.ui.navigation

sealed class NavRoutes(val route: String) {
    object Overview : NavRoutes("overview")
    object Profile : NavRoutes("profile")
}