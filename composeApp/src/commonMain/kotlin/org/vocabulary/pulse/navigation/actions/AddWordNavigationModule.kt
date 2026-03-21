package org.vocabulary.pulse.navigation.actions

import org.koin.dsl.module
import org.vocabulary.pulse.feature.add.di.AddWordNavigationActions
import org.vocabulary.pulse.feature.home.di.HomeRoute
import org.vocabulary.pulse.navigation.api.NavIntent
import org.vocabulary.pulse.navigation.api.NavOptions
import org.vocabulary.pulse.navigation.api.Navigator

val addWordNavigationModule = module {
    single<AddWordNavigationActions> { AddWordNavigationActionsImpl(get()) }
}

private class AddWordNavigationActionsImpl(
    private val navigator: Navigator
) : AddWordNavigationActions {
    override fun navigateToHome() {
        navigator.navigate(
            NavIntent.To(
                route = HomeRoute,
                options = NavOptions(
                    popUpTo = NavIntent.PopUpTo(
                        route = HomeRoute,
                        inclusive = true,
                        saveState = false
                    ),
                    singleTop = true,
                    restoreState = false
                )
            )
        )
    }
}
