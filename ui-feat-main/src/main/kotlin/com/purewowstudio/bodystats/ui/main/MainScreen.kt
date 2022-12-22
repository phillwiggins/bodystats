package com.purewowstudio.bodystats.ui.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.navigation.compose.rememberNavController
import com.purewowstudio.bodystats.ui.common.components.BSBottomNavigationBar
import com.purewowstudio.bodystats.ui.common.components.BSTopAppBar
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import com.purewowstudio.bodystats.ui.navigation.NavRoute
import com.purewowstudio.bodystats.ui.navigation.NavigationManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navigationManager: NavigationManager) {

    val navController = rememberNavController()
    val isDetailView = remember { mutableStateOf(false) }
    val navName = remember { mutableStateOf("") }

    navigationManager.routes.collectAsState().value.also { command ->
        if (command.route.isNotEmpty()) {
            if (command.route == "back") {
                navController.popBackStack()
            } else {
                navController.navigate(command.route)
            }
        }
    }

    BodyStatsTheme {
        Scaffold(
            topBar = {
                BSTopAppBar(
                    title = navName.value.toUpperCase(Locale.current),
                    isNavigationIconDisplayed = isDetailView.value,
                    onBackButtonClicked = { navigationManager.navigate(NavRoute.Back) }
                )
            },
            content = { padding ->
                NavigationHost(
                    paddingValues = padding,
                    navController = navController,
                    onDetailViewDisplayed = {
                        isDetailView.value = it
                    },
                    setNavName = { navName.value = it ?: "" },
                )
            },
            bottomBar = {
                if (!isDetailView.value) {
                    BSBottomNavigationBar(
                        navController = navController
                    )
                }
            }
        )
    }
}