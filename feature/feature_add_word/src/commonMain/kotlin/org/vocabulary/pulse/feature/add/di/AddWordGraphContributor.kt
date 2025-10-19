package org.vocabulary.pulse.feature.add.di

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import org.vocabulary.pulse.feature.add.ui.AddWordScreen
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.Destination
import org.vocabulary.pulse.navigation.api.DestinationRegistry
import org.vocabulary.pulse.navigation.api.GraphContributor

class AddWordGraphContributor : GraphContributor {
    override fun contribute(into: DestinationRegistry) {
        into.register(AddWordDestination)
    }
}

@Serializable
object AddWordRoute : AppRoute

object AddWordDestination : Destination {
    override val route: AppRoute = AddWordRoute

    @Composable
    fun Content(onNavigateBack: () -> Unit) {
        AddWordScreen(onNavigateBack = onNavigateBack)
    }
}
