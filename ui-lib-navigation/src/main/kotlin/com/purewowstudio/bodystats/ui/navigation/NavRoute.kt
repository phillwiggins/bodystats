package com.purewowstudio.bodystats.ui.navigation

sealed class NavRoute(val route: String, val title: String? = null) {
    object Overview : NavRoute("overview", "Overview")
    object Profile : NavRoute("profile", "Profile")
    object Sleep : NavRoute("sleep", "Sleep")
    object Weight : NavRoute("weight", "Weight")
    object Default: NavRoute("")
    object Back: NavRoute("back")
}