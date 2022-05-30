package com.purewowstudio.bodystats.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Overview",
            image = Icons.Filled.Home,
            route = "overview"
        ),
        BarItem(
            title = "Profile",
            image = Icons.Filled.Face,
            route = "profile"
        ),
    )
}