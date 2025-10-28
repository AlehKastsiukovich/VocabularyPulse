package org.vacabulary.pulse.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

internal interface RoomDatabaseProvider {
    fun provideRoomDatabase(): VocaPulseDatabase
}

internal class RoomDatabaseProviderImpl(
    private val builder: RoomDatabase.Builder<VocaPulseDatabase>
) : RoomDatabaseProvider {
    override fun provideRoomDatabase(): VocaPulseDatabase =
        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.Default)
            .build()
}