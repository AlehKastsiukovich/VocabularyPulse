package org.vocabulary.pulse.navigation.actions

import org.koin.dsl.module
import org.vocabulary.pulse.feature.add.di.AddWordRoute
import org.vocabulary.pulse.feature.card.di.CardRoute
import org.vocabulary.pulse.feature.home.di.HomeNavigationActions
import org.vocabulary.pulse.navigation.api.NavIntent
import org.vocabulary.pulse.navigation.api.Navigator

val homeNavigationModule = module {
    single<HomeNavigationActions> { HomeNavigationActionsImpl(get()) }
}

private class HomeNavigationActionsImpl(
    private val navigator: Navigator
) : HomeNavigationActions {
    override fun openTraining() {
        navigator.navigate(NavIntent.To(CardRoute))
    }

    override fun openAddWord() {
        navigator.navigate(NavIntent.To(AddWordRoute))
    }
}
