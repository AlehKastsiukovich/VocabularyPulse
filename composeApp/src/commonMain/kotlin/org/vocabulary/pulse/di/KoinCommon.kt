package org.vocabulary.pulse.di

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.module.Module
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module
import org.vocabulary.pulse.App
import org.vocabulary.pulse.feature.home.di.homeScreenModule

@Preview
@Composable
fun KoinApp() =
    KoinMultiplatformApplication(
        config = KoinConfiguration {
            modules(module {  }.plus(provideAllModules()))
        }
    ) {
        App()
    }

fun provideAllModules(): List<Module> = listOf(
    homeScreenModule
)
