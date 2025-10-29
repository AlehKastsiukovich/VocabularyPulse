package org.vacabulary.pulse.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.vocabulary.pulse.core.common.dispatcher.AppDispatchers

internal interface RoomDatabaseProvider {
    fun provideRoomDatabase(): VocaPulseDatabase
}

internal class RoomDatabaseProviderImpl(
    private val builder: RoomDatabase.Builder<VocaPulseDatabase>,
    private val appDispatchers: AppDispatchers
) : RoomDatabaseProvider {
    override fun provideRoomDatabase(): VocaPulseDatabase =
        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(appDispatchers.io)
            .build()
}