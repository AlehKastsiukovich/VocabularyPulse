package org.vacabulary.pulse.data.model

import org.vacabulary.pulse.database.model.WordEntity

data class Word(
    val id: Long = 0,
    val word: String,
    val translation: String,
    val examples: List<String> = emptyList()
)

internal fun WordEntity.asExternalModel() = Word(
    id = id,
    word = word,
    translation = translation,
    examples = examples
)

internal fun Word.asInternalModel() = WordEntity(
    id = id,
    word = word,
    translation = translation,
    examples = examples
)