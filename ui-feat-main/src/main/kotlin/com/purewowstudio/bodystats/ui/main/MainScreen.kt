package com.purewowstudio.bodystats.ui.main

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.purewowstudio.bodystats.ui.common.components.BSBottomNavigationBar
import com.purewowstudio.bodystats.ui.common.components.BSTopAppBar
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val navName = remember { mutableStateOf("") }

    BodyStatsTheme {
        Scaffold(
            topBar = {
                BSTopAppBar(title = navName.value)
            },
            content = { padding ->
                NavigationHost(
                    paddingValues = padding,
                    navController = navController
                )
            },
            bottomBar = {
                BSBottomNavigationBar(
                    navController = navController,
                    setNavName = { navName.value = it }
                )
            }
        )
    }
}