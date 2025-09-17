package org.vocabulary.pulse.feature.home.di

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import org.vocabulary.pulse.feature.home.ui.HomeScreen
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.Destination
import org.vocabulary.pulse.navigation.api.DestinationRegistry
import org.vocabulary.pulse.navigation.api.GraphContributor
import org.vocabulary.pulse.navigation.api.Navigator

class HomeGraphContributor : GraphContributor {
    override fun contribute(into: DestinationRegistry) {
        into.register(HomeDestination)
    }
}

@Serializable
object HomeRoute : AppRoute

object HomeDestination : Destination {
    override val route: AppRoute = HomeRoute

    @Composable
    override fun Content(route: AppRoute, navigator: Navigator) {
        HomeScreen()
    }
}