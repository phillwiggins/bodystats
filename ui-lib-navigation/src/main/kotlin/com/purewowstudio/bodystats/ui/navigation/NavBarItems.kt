package com.purewowstudio.bodystats.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Overview",
            image = Icons.Outlined.Dashboard,
            route = "overview"
        ),
        BarItem(
            title = "Profile",
            image = Icons.Outlined.Person,
            route = "profile"
        ),
    )
}