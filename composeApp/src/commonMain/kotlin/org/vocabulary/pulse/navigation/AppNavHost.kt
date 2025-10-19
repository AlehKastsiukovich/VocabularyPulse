package org.vocabulary.pulse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.vocabulary.pulse.feature.add.di.AddWordDestination
import org.vocabulary.pulse.feature.add.di.AddWordRoute
import org.vocabulary.pulse.feature.home.di.HomeDestination
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.DestinationRegistry
import org.vocabulary.pulse.navigation.api.NavIntent
import org.vocabulary.pulse.navigation.api.NavOptions
import org.vocabulary.pulse.navigation.api.Navigator
import org.vocabulary.pulse.navigation.compose.ComposeNavigator

@Composable
fun AppNavHost(
    registry: DestinationRegistry,
    startRoute: AppRoute,
) {
    val controller: NavHostController = rememberNavController()
    val navigator: Navigator = ComposeNavigator(controller)

    NavHost(
        navController = controller,
        startDestination = startRoute
    ) {
        registry.destinations.forEach { destination ->
            when(destination) {
                is HomeDestination -> composable(destination.route::class) { _ ->
                    destination.Content(
                        onStartTraining = { },
                        onAddWord = { navigator.navigate(NavIntent.To(AddWordRoute)) }
                    )
                }
                is AddWordDestination -> composable(destination.route::class) { _ ->
                    destination.Content(
                        onNavigateBack = { navigator.back() },
                        onNavigateToHome = {
                            navigator.navigate(
                                NavIntent.To(
                                    route = HomeDestination.route,
                                    options = NavOptions(
                                        popUpTo = NavIntent.PopUpTo(
                                            route = HomeDestination.route,
                                            inclusive = true,
                                            saveState = false
                                        ),
                                        singleTop = true,
                                        restoreState = false
                                    )
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}