package org.vocabulary.pulse.navigation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.DestinationRegistry

@Composable
fun AppNavHost(
    registry: DestinationRegistry,
    startRoute: AppRoute,
) {
    val controller: NavHostController = rememberNavController()
    val navigator = ComposeNavigator(controller)

    NavHost(
        navController = controller,
        startDestination = startRoute
    ) {
        registry.destinations.forEach { destination ->
            composable(destination.route::class) { _ ->
                destination.Content(destination.route, navigator)
            }
        }
    }
}