package org.vocabulary.pulse.feature.add.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.vocabulary.pulse.feature.add.ui.AddWordScreenViewModel

val addWordScreenModule = module {
    viewModel { AddWordScreenViewModel() }
    single { AddWordGraphContributor() }
}