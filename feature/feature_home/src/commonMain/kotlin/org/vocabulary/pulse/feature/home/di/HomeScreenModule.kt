package org.vocabulary.pulse.feature.home.di

import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.vocabulary.pulse.feature.home.ui.HomeScreenViewModel
import org.vocabulary.pulse.navigation.entry.api.NavigationDestination

val homeScreenModule = module {
    viewModel { HomeScreenViewModel() }
    single<NavigationDestination>(named(HomeRoute.path)) { HomeDestination(get()) }
}
