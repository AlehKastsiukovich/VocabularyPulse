package org.vocabulary.pulse

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.vocabulary.pulse.feature.add.di.AddWordGraphContributor
import org.vocabulary.pulse.feature.home.di.HomeGraphContributor
import org.vocabulary.pulse.feature.home.di.HomeRoute
import org.vocabulary.pulse.navigation.api.AppRoute
import org.vocabulary.pulse.navigation.api.GraphContributor
import org.vocabulary.pulse.navigation.AppNavHost
import org.vocabulary.pulse.navigation.compose.ComposeDestinationRegistry

@Composable
@Preview
fun App() {
    val contributors: List<GraphContributor> = listOf(
        koinInject<HomeGraphContributor>(),
        koinInject<AddWordGraphContributor>()
    )

    val registry = ComposeDestinationRegistry().also { reg ->
        contributors.forEach { it.contribute(reg) }
    }
    val startRoute: AppRoute = HomeRoute

    MaterialTheme {
        AppNavHost(
            registry = registry,
            startRoute = startRoute,
        )
    }
}