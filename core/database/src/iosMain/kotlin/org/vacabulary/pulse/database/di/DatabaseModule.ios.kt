package org.vacabulary.pulse.database.di

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import org.vacabulary.pulse.database.VocaPulseDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual val databasePlatformModule: Module = module {
    single<RoomDatabase.Builder<VocaPulseDatabase>> {
        getDatabaseBuilder()
    }
}

internal fun getDatabaseBuilder(): RoomDatabase.Builder<VocaPulseDatabase> {
    val dbFilePath = documentDirectory() + VOCA_PULSE_DATABASE_NAME
    return Room.databaseBuilder<VocaPulseDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
