@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.vacabulary.pulse.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import org.vacabulary.pulse.database.converter.VocaPulseEntityConverter
import org.vacabulary.pulse.database.dao.WordsDao
import org.vacabulary.pulse.database.model.WordEntity

@Database(
    version = 1,
    entities = [WordEntity::class],
    exportSchema = true
)
@ConstructedBy(VocaPulseDatabaseConstructor::class)
@TypeConverters(VocaPulseEntityConverter::class)
abstract class VocaPulseDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
}

@Suppress("KotlinNoActualForExpect")
internal expect object VocaPulseDatabaseConstructor : RoomDatabaseConstructor<VocaPulseDatabase> {
    override fun initialize(): VocaPulseDatabase
}