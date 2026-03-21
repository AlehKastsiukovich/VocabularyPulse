package org.vocabulary.pulse.feature.add.di

import androidx.compose.runtime.Composable
import org.vocabulary.pulse.feature.add.presenter.ui.AddWordScreen
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.Navigator
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination

class AddWordDestination(
    private val navigator: Navigator,
    private val navigationActions: AddWordNavigationActions
) : NavigationDestination {
    override val route: AppRoute = AddWordRoute

    @Composable
    override fun Content() {
        AddWordScreen(
            onNavigateBack = navigator::back,
            onNavigateToHome = navigationActions::navigateToHome
        )
    }
}

object AddWordRoute : AppRoute {
    override val path: String = "add_word"
}
