package org.vocabulary.pulse.feature.card.di

import androidx.compose.runtime.Composable
import org.vocabulary.pulse.feature.card.presenter.ui.CardScreen
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.Navigator
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination

class CardDestination(
    private val navigator: Navigator
) : NavigationDestination {
    override val route: AppRoute = CardRoute

    @Composable
    override fun Content() {
        CardScreen(
            onNavigateBack = navigator::back
        )
    }
}

object CardRoute : AppRoute {
    override val path: String = "training"
}
