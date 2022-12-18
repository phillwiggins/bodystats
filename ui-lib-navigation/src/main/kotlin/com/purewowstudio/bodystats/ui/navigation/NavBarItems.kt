package com.purewowstudio.bodystats.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Person

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Overview",
            image = Icons.Outlined.Dashboard,
            route = NavRoute.Overview
        ),
        BarItem(
            title = "Profile",
            image = Icons.Outlined.Person,
            route = NavRoute.Profile
        ),
    )
}