package org.vocabulary.pulse.navigation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.DestinationRegistry
import org.vocabulary.pulse.navigation.api.Navigator

@Composable
fun AppNavHost(
    registry: DestinationRegistry,
    startRoute: AppRoute,
    navigator: Navigator
) {
    val controller: NavHostController = rememberNavController()

    NavHost(
        navController = controller,
        startDestination = startRoute
    ) {
        registry.destinations.forEach { destination ->
            composable(destination.route::class) { navBackStackEntry ->
                destination.Content(destination.route, navigator)
            }
        }
    }
}