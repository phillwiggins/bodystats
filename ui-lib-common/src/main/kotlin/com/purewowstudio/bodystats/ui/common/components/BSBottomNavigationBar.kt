package com.purewowstudio.bodystats.ui.common.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.navigation.NavBarItems

@Composable
fun BSBottomNavigationBar(
    navController: NavHostController,
    setNavName: (String) -> Unit,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
    ) {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            val isSelected = currentRoute == navItem.route
            if (isSelected) setNavName(navItem.title)

            BottomNavigationItem(
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.surface,
                selected = isSelected,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                        text = navItem.title,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
            )
        }
    }
}

@Composable
@Preview
fun BottomAppBarPreview() {
    val navController = rememberNavController()
    BodyStatsTheme {
        BSBottomNavigationBar(navController = navController, setNavName = {})
    }
}
