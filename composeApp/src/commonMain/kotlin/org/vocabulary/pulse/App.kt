package org.vocabulary.pulse

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.vocabulary.pulse.feature.add.di.AddWordGraphContributor
import org.vocabulary.pulse.feature.home.di.HomeGraphContributor
import org.vocabulary.pulse.feature.home.di.HomeRoute
import org.vocabulary.pulse.navigation.AppNavHost
import org.vocabulary.pulse.navigation.compose.ComposeDestinationRegistry

@Composable
@Preview
fun App() {
    val homeGraphContributor = koinInject<HomeGraphContributor>()
    val addWordGraphContributor = koinInject<AddWordGraphContributor>()

    val registry = ComposeDestinationRegistry().apply {
        homeGraphContributor.contribute(this)
        addWordGraphContributor.contribute(this)
    }

    MaterialTheme {
        AppNavHost(
            registry = registry,
            startRoute = HomeRoute,
        )
    }
}