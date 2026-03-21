package org.vocabulary.pulse.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.NavigatorHolder
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination

@Composable
fun ComposeNavigationHost(
    destinations: List<NavigationDestination>,
    startRoute: AppRoute,
    navigatorHolder: NavigatorHolder,
) {
    val routes = remember(destinations) { destinations.map { it.route.path } }
    require(routes.size == routes.toSet().size) {
        "Duplicate navigation routes found: $routes"
    }

    val controller = rememberNavController()
    val navigator = remember(controller) { ComposeNavigator(controller) }

    DisposableEffect(navigatorHolder, navigator) {
        navigatorHolder.attach(navigator)
        onDispose {
            navigatorHolder.detach(navigator)
        }
    }

    NavHost(
        navController = controller,
        startDestination = startRoute.path
    ) {
        destinations.forEach { destination ->
            composable(destination.route.path) {
                destination.Content()
            }
        }
    }
}
