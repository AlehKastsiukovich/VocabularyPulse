package org.vocabulary.pulse.feature.home.di

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf
import org.vocabulary.pulse.feature.home.ui.HomeScreen
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.Destination
import org.vocabulary.pulse.navigation.api.DestinationRegistry
import org.vocabulary.pulse.navigation.api.GraphContributor

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
    fun Content(
        onStartTraining: () -> Unit = {},
        onAddWord: () -> Unit = {}
    ) {
        val koin = getKoin()
        HomeScreen(
            onStartTraining = onStartTraining,
            onAddWord = onAddWord,
            viewModelProvider = { koin.get { parametersOf() } }
        )
    }
}
