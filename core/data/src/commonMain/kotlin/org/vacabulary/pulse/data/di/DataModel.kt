package org.vacabulary.pulse.data.di

import org.koin.dsl.module
import org.vacabulary.pulse.data.repository.WordsContentRepository
import org.vacabulary.pulse.data.repository.WordsContentRepositoryImpl

val dataModule = module {
    single<WordsContentRepository> { WordsContentRepositoryImpl(wordsDao = get()) }
}