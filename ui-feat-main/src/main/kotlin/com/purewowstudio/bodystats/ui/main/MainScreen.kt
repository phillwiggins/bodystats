package com.purewowstudio.bodystats.ui.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.purewowstudio.bodystats.ui.common.components.BSBottomNavigationBar
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    BodyStatsTheme {
        Scaffold(
            content = {
                NavigationHost(navController = navController)
            },
            bottomBar = {
                BSBottomNavigationBar(navController = navController)
            }
        )
    }
}