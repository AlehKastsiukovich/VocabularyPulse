package org.vocabulary.pulse.feature.add.di

import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.vocabulary.pulse.feature.add.presenter.model.AddWordMiddleware
import org.vocabulary.pulse.feature.add.presenter.viewmodel.AddWordScreenViewModel
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination

val addWordScreenModule = module {
    viewModel {
        AddWordScreenViewModel(
            wordsContentRepository = get(),
            middleware = get(),
            appDispatchers = get()
        )
    }
    single<NavigationDestination>(named(AddWordRoute.path)) { AddWordDestination(get(), get()) }
    factory { AddWordMiddleware() }
}
