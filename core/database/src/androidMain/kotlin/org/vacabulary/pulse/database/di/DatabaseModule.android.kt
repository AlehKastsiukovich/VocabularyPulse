package org.vacabulary.pulse.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.vacabulary.pulse.database.VocaPulseDatabase

actual val databasePlatformModule: Module = module {
    single<RoomDatabase.Builder<VocaPulseDatabase>> {
        getDatabaseBuilder(androidContext())
    }
}

internal fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<VocaPulseDatabase> {
  val appContext = context.applicationContext
  val dbFile = appContext.getDatabasePath(VOCA_PULSE_DATABASE_NAME)
  return Room.databaseBuilder<VocaPulseDatabase>(
    context = appContext,
    name = dbFile.absolutePath
  )
}
