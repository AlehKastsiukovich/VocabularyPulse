package org.vacabulary.pulse.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.vacabulary.pulse.data.model.Word
import org.vacabulary.pulse.data.model.asExternalModel
import org.vacabulary.pulse.data.model.asInternalModel
import org.vacabulary.pulse.database.dao.WordsDao

internal class WordsContentRepositoryImpl(
    private val wordsDao: WordsDao
) : WordsContentRepository {
    override fun getWords(): Flow<List<Word>> =
        wordsDao.getAllWithExamples().map { wordEntities ->
            wordEntities.map { it.asExternalModel() }
        }

    override suspend fun addWord(word: Word): Long = wordsDao.insertWord(word.asInternalModel())
}