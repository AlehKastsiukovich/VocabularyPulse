package org.vocabulary.pulse.navigation

import androidx.compose.runtime.Composable
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.NavigatorHolder
import org.vocabulary.pulse.navigation.compose.ComposeNavigationHost
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination

@Composable
fun AppNavHost(
    destinations: List<NavigationDestination>,
    startRoute: AppRoute,
    navigatorHolder: NavigatorHolder,
) {
    ComposeNavigationHost(
        destinations = destinations,
        startRoute = startRoute,
        navigatorHolder = navigatorHolder
    )
}
