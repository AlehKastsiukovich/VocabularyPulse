package org.vocabulary.pulse.feature.add.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.vocabulary.pulse.feature.add.presenter.model.AddWordMiddleware
import org.vocabulary.pulse.feature.add.presenter.viewmodel.AddWordScreenViewModel

val addWordScreenModule = module {
    viewModel {
        AddWordScreenViewModel(
            wordsContentRepository = get(),
            middleware = get(),
            appDispatchers = get()
        )
    }
    single { AddWordGraphContributor() }
    factory { AddWordMiddleware() }
}