package org.vocabulary.pulse.feature.card.di

import org.koin.dsl.module

val cardModule = module {
    single { CardGraphContributor() }
}