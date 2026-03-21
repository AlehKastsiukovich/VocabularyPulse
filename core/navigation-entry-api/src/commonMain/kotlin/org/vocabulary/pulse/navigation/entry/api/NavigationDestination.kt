package org.vocabulary.pulse.navigation.entry.api

import androidx.compose.runtime.Composable
import org.vocabulary.pulse.navigation.api.AppRoute

interface NavigationDestination {
    val route: AppRoute

    @Composable
    fun Content()
}
