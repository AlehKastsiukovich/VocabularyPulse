package org.vocabulary.pulse.navigation

import org.koin.dsl.module
import org.vocabulary.pulse.navigation.actions.addWordNavigationModule
import org.vocabulary.pulse.navigation.actions.homeNavigationModule
import org.vocabulary.pulse.navigation.api.Navigator
import org.vocabulary.pulse.navigation.api.NavigatorHolder

val appNavigationModule = module {
    includes(
        homeNavigationModule,
        addWordNavigationModule
    )

    single { NavigatorHolder() }
    single<Navigator> { get<NavigatorHolder>() }
}
