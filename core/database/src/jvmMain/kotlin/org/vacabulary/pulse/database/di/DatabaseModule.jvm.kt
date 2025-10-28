package org.vacabulary.pulse.database.di

import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import org.vacabulary.pulse.database.VocaPulseDatabase
import java.io.File

actual val databasePlatformModule: Module = module {
    single<RoomDatabase.Builder<VocaPulseDatabase>> {
        getDatabaseBuilder()
    }
}

internal fun getDatabaseBuilder(): RoomDatabase.Builder<VocaPulseDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), VOCA_PULSE_DATABASE_NAME)
    return Room.databaseBuilder<VocaPulseDatabase>(
        name = dbFile.absolutePath,
    )
}
