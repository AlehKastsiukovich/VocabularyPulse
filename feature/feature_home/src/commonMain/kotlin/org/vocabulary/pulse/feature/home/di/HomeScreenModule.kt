package org.vocabulary.pulse.feature.home.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.vocabulary.pulse.feature.home.ui.HomeScreenViewModel

val homeScreenModule = module {
    viewModel { HomeScreenViewModel() }
}