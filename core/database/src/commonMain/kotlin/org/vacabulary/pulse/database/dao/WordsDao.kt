package org.vacabulary.pulse.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.vacabulary.pulse.database.model.WordEntity

@Dao
interface WordsDao {
    @Query("SELECT * FROM words")
    fun getAllWithExamples(): Flow<List<WordEntity>>
    @Insert
    suspend fun insertWord(word: WordEntity): Long
}