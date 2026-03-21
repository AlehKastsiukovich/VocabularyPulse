package org.vocabulary.pulse.feature.home.di

import androidx.compose.runtime.Composable
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf
import org.vocabulary.pulse.feature.home.ui.HomeScreen
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination

class HomeDestination(
    private val navigationActions: HomeNavigationActions
) : NavigationDestination {
    override val route: AppRoute = HomeRoute

    @Composable
    override fun Content() {
        val koin = getKoin()
        HomeScreen(
            onStartTraining = navigationActions::openTraining,
            onAddWord = navigationActions::openAddWord,
            viewModelProvider = { koin.get { parametersOf() } }
        )
    }
}

object HomeRoute : AppRoute {
    override val path: String = "home"
}
