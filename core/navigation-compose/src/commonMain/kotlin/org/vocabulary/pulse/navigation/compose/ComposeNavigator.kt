package org.vocabulary.pulse.navigation.compose

import androidx.navigation.NavHostController
import org.vocabulary.pulse.navigation.api.NavIntent
import org.vocabulary.pulse.navigation.api.Navigator

class ComposeNavigator(
    private val controller: NavHostController
) : Navigator {

    override fun navigate(navIntent: NavIntent) {
        when (navIntent) {
            is NavIntent.To -> {
                val options = navIntent.options
                val builder: androidx.navigation.NavOptionsBuilder.() -> Unit = {
                    launchSingleTop = options.singleTop
                    restoreState = options.restoreState
                    options.popUpTo?.let { pop ->
                        popUpTo(pop.route) {
                            inclusive = pop.inclusive
                            saveState = pop.saveState
                        }
                    }
                }
                controller.navigate(navIntent.route, builder)
            }
            is NavIntent.Replace -> {
                controller.navigate(navIntent.route) {
                    popUpTo(controller.graph.startDestinationId) { inclusive = false }
                    launchSingleTop = true
                }
            }
            is NavIntent.PopUpTo -> {
                controller.popBackStack(navIntent.route, navIntent.inclusive)
            }
            is NavIntent.NewRoot -> {
                controller.navigate(navIntent.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
            NavIntent.Up -> controller.navigateUp()
            NavIntent.Back -> back()
        }
    }

    override fun canGoBack(): Boolean = controller.previousBackStackEntry != null

    override fun back() {
        controller.popBackStack()
    }
}
