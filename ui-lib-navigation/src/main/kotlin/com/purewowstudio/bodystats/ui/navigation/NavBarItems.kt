package com.purewowstudio.bodystats.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Overview",
            image = Icons.Filled.Home,
            route = "overview"
        ),
        BarItem(
            title = "Profile",
            image = Icons.Filled.Person,
            route = "profile"
        ),
    )
}