package org.vocabulary.pulse.feature.card.di

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import org.vocabulary.pulse.feature.card.presenter.ui.CardScreen
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.Destination
import org.vocabulary.pulse.navigation.api.DestinationRegistry
import org.vocabulary.pulse.navigation.api.GraphContributor

class CardGraphContributor : GraphContributor {
    override fun contribute(into: DestinationRegistry) {
        into.register(CardDestination)
    }
}

@Serializable
object CardRoute : AppRoute

object CardDestination : Destination {
    override val route: AppRoute = CardRoute

    @Composable
    fun Content(
        onNavigateBack: () -> Unit = {},
    ) {
        CardScreen(
            onNavigateBack = onNavigateBack
        )
    }
}
