package org.vacabulary.pulse.database.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.vacabulary.pulse.database.RoomDatabaseProvider
import org.vacabulary.pulse.database.RoomDatabaseProviderImpl
import org.vacabulary.pulse.database.VocaPulseDatabase
import org.vacabulary.pulse.database.dao.WordsDao

internal const val VOCA_PULSE_DATABASE_NAME = "voca_pulse_database.db"

val databaseModule = module {
    single<RoomDatabaseProvider> {
        RoomDatabaseProviderImpl(builder = get(), appDispatchers = get())
    }
    single<VocaPulseDatabase> {
        get<RoomDatabaseProvider>().provideRoomDatabase()
    }
    single<WordsDao> { get<VocaPulseDatabase>().wordsDao() }
}

expect val databasePlatformModule: Module