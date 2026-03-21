package org.vocabulary.pulse

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.getKoin
import org.vocabulary.pulse.feature.home.di.HomeRoute
import org.vocabulary.pulse.navigation.AppNavHost
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination
import org.vocabulary.pulse.navigation.api.NavigatorHolder

@Composable
@Preview
fun App() {
    val koin = getKoin()
    val navigatorHolder = koinInject<NavigatorHolder>()
    val destinations = remember(koin) { koin.getAll<NavigationDestination>() }

    MaterialTheme {
        AppNavHost(
            destinations = destinations,
            startRoute = HomeRoute,
            navigatorHolder = navigatorHolder,
        )
    }
}
