package org.vocabulary.pulse.feature.card.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination

val cardModule = module {
    single<NavigationDestination>(named(CardRoute.path)) { CardDestination(get()) }
}
