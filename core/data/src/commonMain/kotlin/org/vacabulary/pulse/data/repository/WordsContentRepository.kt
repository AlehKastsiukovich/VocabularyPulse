package org.vacabulary.pulse.data.repository

import kotlinx.coroutines.flow.Flow
import org.vacabulary.pulse.data.model.Word

interface WordsContentRepository {
    fun getWords(): Flow<List<Word>>
    suspend fun addWord(word: Word): Long
}
