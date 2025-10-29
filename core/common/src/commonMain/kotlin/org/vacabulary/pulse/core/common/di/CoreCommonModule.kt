package org.vacabulary.pulse.core.common.di

import org.koin.dsl.module
import org.vocabulary.pulse.core.common.dispatcher.AppDispatchers
import org.vocabulary.pulse.core.common.dispatcher.provideAppDispatchers

val coreCommonModule = module {
    single<AppDispatchers> { provideAppDispatchers() }
}