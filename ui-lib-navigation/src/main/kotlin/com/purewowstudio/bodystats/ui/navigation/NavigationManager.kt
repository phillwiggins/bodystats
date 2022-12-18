package com.purewowstudio.bodystats.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {
    var routes = MutableStateFlow<NavRoute>(NavRoute.Default)

    fun navigate(route: NavRoute) {
        this.routes.value = route
    }
}