package org.vocabulary.pulse.di

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.Module
import org.koin.dsl.KoinConfiguration
import org.vacabulary.pulse.core.common.di.coreCommonModule
import org.vacabulary.pulse.data.di.dataModule
import org.vacabulary.pulse.database.di.databaseModule
import org.vacabulary.pulse.database.di.databasePlatformModule
import org.vocabulary.pulse.App
import org.vocabulary.pulse.feature.add.di.addWordScreenModule
import org.vocabulary.pulse.feature.card.di.cardModule
import org.vocabulary.pulse.feature.home.di.homeScreenModule

@OptIn(KoinExperimentalAPI::class)
@Preview
@Composable
fun KoinApp() {
    KoinMultiplatformApplication(
        config = KoinConfiguration {
            modules(provideAllModules())
        }
    ) {
        App()
    }
}

private fun provideAllModules(): List<Module> = listOf(
    databasePlatformModule,
    databaseModule,
    dataModule,
    homeScreenModule,
    addWordScreenModule,
    coreCommonModule,
    cardModule
)
